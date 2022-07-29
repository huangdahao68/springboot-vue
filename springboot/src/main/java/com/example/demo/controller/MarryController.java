package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Marry;
import com.example.demo.mapper.MarryMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/marry")
public class MarryController {

    @Resource
    MarryMapper MarryMapper;


    @PostMapping
    public Result<?> save(@RequestBody Marry Marry) {
        MarryMapper.insert(Marry);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody Marry Marry) {
        MarryMapper.updateById(Marry);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> update(@PathVariable Long id) {
        MarryMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> getById(@PathVariable Long id) {
        return Result.success(MarryMapper.selectById(id));
    }

    @GetMapping("/all")
    public Result<?> findAll() {
        return Result.success(MarryMapper.selectList(null));
    }

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "20") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        LambdaQueryWrapper<Marry> wrapper = Wrappers.<Marry>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(Marry::getMname, search);
        }
        Page<Marry> MarryPage = MarryMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(MarryPage);
    }
}
