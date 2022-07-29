package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("bigphoto")
@Data
public class BigPhoto {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String hname;
    private String htitle;
    private String hdescription;
    private String himg;
    private String himg1;
}
