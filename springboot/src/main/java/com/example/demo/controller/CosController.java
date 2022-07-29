package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Cos;
import com.example.demo.mapper.CosMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/cos")
public class CosController {

    @Resource
    CosMapper CosMapper;


    @PostMapping
    public Result<?> save(@RequestBody Cos Cos) {
        CosMapper.insert(Cos);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody Cos Cos) {
        CosMapper.updateById(Cos);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> update(@PathVariable Long id) {
        CosMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> getById(@PathVariable Long id) {
        return Result.success(CosMapper.selectById(id));
    }

    @GetMapping("/all")
    public Result<?> findAll() {
        return Result.success(CosMapper.selectList(null));
    }

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "20") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        LambdaQueryWrapper<Cos> wrapper = Wrappers.<Cos>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(Cos::getCname, search);
        }
        Page<Cos> CosPage = CosMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(CosPage);
    }
}
