package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.LiFu;
import com.example.demo.mapper.LiFuMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/lifu")
public class LiFuController {

    @Resource
    LiFuMapper LiFuMapper;


    @PostMapping
    public Result<?> save(@RequestBody LiFu LiFu) {
        LiFuMapper.insert(LiFu);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody LiFu LiFu) {
        LiFuMapper.updateById(LiFu);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> update(@PathVariable Long id) {
        LiFuMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> getById(@PathVariable Long id) {
        return Result.success(LiFuMapper.selectById(id));
    }

    @GetMapping("/all")
    public Result<?> findAll() {
        return Result.success(LiFuMapper.selectList(null));
    }

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "20") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        LambdaQueryWrapper<LiFu> wrapper = Wrappers.<LiFu>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(LiFu::getLname, search);
        }
        Page<LiFu> LiFuPage = LiFuMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(LiFuPage);
    }
}
