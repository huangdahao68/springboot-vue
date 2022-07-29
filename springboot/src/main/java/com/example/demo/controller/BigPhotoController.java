package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.BigPhoto;
import com.example.demo.mapper.BigPhotoMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/bigphoto")
public class BigPhotoController {

    @Resource
    BigPhotoMapper BigPhotoMapper;


    @PostMapping
    public Result<?> save(@RequestBody BigPhoto BigPhoto) {
        BigPhotoMapper.insert(BigPhoto);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody BigPhoto BigPhoto) {
        BigPhotoMapper.updateById(BigPhoto);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> update(@PathVariable Long id) {
        BigPhotoMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> getById(@PathVariable Long id) {
        return Result.success(BigPhotoMapper.selectById(id));
    }

    @GetMapping("/all")
    public Result<?> findAll() {
        return Result.success(BigPhotoMapper.selectList(null));
    }

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        LambdaQueryWrapper<BigPhoto> wrapper = Wrappers.<BigPhoto>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(BigPhoto::getHname, search);
        }
        Page<BigPhoto> BigPhotoPage = BigPhotoMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(BigPhotoPage);
    }
}
