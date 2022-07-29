package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("smallphoto")
@Data
public class SmallPhoto {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String sname;
    private String sphoto;
}
