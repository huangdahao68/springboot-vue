package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.XieZhen;
import com.example.demo.mapper.XieZhenMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/xiezhen")
public class XieZhenController {

    @Resource
    XieZhenMapper XieZhenMapper;


    @PostMapping
    public Result<?> save(@RequestBody XieZhen XieZhen) {
        XieZhenMapper.insert(XieZhen);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody XieZhen XieZhen) {
        XieZhenMapper.updateById(XieZhen);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> update(@PathVariable Long id) {
        XieZhenMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> getById(@PathVariable Long id) {
        return Result.success(XieZhenMapper.selectById(id));
    }

    @GetMapping("/all")
    public Result<?> findAll() {
        return Result.success(XieZhenMapper.selectList(null));
    }

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "20") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        LambdaQueryWrapper<XieZhen> wrapper = Wrappers.<XieZhen>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(XieZhen::getXname, search);
        }
        Page<XieZhen> XieZhenPage = XieZhenMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(XieZhenPage);
    }
}
