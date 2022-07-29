package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Doll;
import com.example.demo.mapper.DollMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/doll")
public class DollController {

    @Resource
    DollMapper DollMapper;


    @PostMapping
    public Result<?> save(@RequestBody Doll Doll) {
        DollMapper.insert(Doll);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody Doll Doll) {
        DollMapper.updateById(Doll);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> update(@PathVariable Long id) {
        DollMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> getById(@PathVariable Long id) {
        return Result.success(DollMapper.selectById(id));
    }

    @GetMapping("/all")
    public Result<?> findAll() {
        return Result.success(DollMapper.selectList(null));
    }

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "20") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        LambdaQueryWrapper<Doll> wrapper = Wrappers.<Doll>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(Doll::getDname, search);
        }
        Page<Doll> DollPage = DollMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(DollPage);
    }
}
