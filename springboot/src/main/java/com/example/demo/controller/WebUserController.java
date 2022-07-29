package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.WebUser;
import com.example.demo.mapper.WebUserMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/webuser")
public class WebUserController {

    @Resource
    WebUserMapper webUserMapper;

    @PostMapping("/login")
    public Result<?> login(@RequestBody WebUser webUser) {
        WebUser res = webUserMapper.selectOne(Wrappers.<WebUser>lambdaQuery().eq(WebUser::getName, webUser.getName())
                .eq(WebUser::getPassword, webUser.getPassword()));
        if (res == null) {
            return Result.error("-1", "用户名或密码错误");
        }
        return Result.success(res);
    }

    @PostMapping("/register")
    public Result<?> register(@RequestBody WebUser webUser) {
        WebUser res = webUserMapper.selectOne(Wrappers.<WebUser>lambdaQuery().eq(WebUser::getName, webUser.getName()));
        if (res != null) {
            return Result.error("-1", "用户名重复");
        }
        if (webUser.getPassword() == null) {
            webUser.setPassword("123456");
        }
        webUserMapper.insert(webUser);
        return Result.success();
    }

    @PostMapping
    public Result<?> save(@RequestBody WebUser WebUser) {
        if (WebUser.getPassword() == null) {
            WebUser.setPassword("123456");
        }
        webUserMapper.insert(WebUser);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody WebUser WebUser) {
        webUserMapper.updateById(WebUser);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> update(@PathVariable Long id) {
        webUserMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> getById(@PathVariable Long id) {
        return Result.success(webUserMapper.selectById(id));
    }

    @GetMapping("/all")
    public Result<?> findAll() {
        return Result.success(webUserMapper.selectList(null));
    }

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        LambdaQueryWrapper<WebUser> wrapper = Wrappers.<WebUser>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(WebUser::getName, search);
        }
        Page<WebUser> WebUserPage = webUserMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(WebUserPage);
    }
}
