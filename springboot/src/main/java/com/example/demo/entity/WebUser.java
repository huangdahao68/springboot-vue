package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("webuser")
@Data
public class WebUser {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String password;
    private String emall;

}
