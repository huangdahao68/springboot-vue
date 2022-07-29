package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("about")
@Data
public class WebAbout {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String acontent;
    private String acontent1;
    private String acontent2;
    private String acontent3;
    private String acontent4;
    private String acontent5;
    private String aimg;
    private String aimg1;
    private String aimg2;
}
