package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.WebAbout;
import com.example.demo.mapper.WebAboutMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/webabout")
public class WebAboutController {

    @Resource
    WebAboutMapper WebAboutMapper;


    @PostMapping
    public Result<?> save(@RequestBody WebAbout WebAbout) {
        WebAboutMapper.insert(WebAbout);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody WebAbout WebAbout) {
        WebAboutMapper.updateById(WebAbout);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> update(@PathVariable Long id) {
        WebAboutMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> getById(@PathVariable Long id) {
        return Result.success(WebAboutMapper.selectById(id));
    }

    @GetMapping("/all")
    public Result<?> findAll() {
        return Result.success(WebAboutMapper.selectList(null));
    }

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        LambdaQueryWrapper<WebAbout> wrapper = Wrappers.<WebAbout>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(WebAbout::getId, search);
        }
        Page<WebAbout> WebAboutPage = WebAboutMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(WebAboutPage);
    }
}
