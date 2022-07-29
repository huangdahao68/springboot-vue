package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.SmallPhoto;
import com.example.demo.mapper.SmallPhotoMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/smallphoto")
public class SmallPhotoController {

    @Resource
    SmallPhotoMapper SmallPhotoMapper;


    @PostMapping
    public Result<?> save(@RequestBody SmallPhoto SmallPhoto) {
        SmallPhotoMapper.insert(SmallPhoto);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody SmallPhoto SmallPhoto) {
        SmallPhotoMapper.updateById(SmallPhoto);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> update(@PathVariable Long id) {
        SmallPhotoMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> getById(@PathVariable Long id) {
        return Result.success(SmallPhotoMapper.selectById(id));
    }

    @GetMapping("/all")
    public Result<?> findAll() {
        return Result.success(SmallPhotoMapper.selectList(null));
    }

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        LambdaQueryWrapper<SmallPhoto> wrapper = Wrappers.<SmallPhoto>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(SmallPhoto::getSname, search);
        }
        Page<SmallPhoto> SmallPhotoPage = SmallPhotoMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(SmallPhotoPage);
    }
}
