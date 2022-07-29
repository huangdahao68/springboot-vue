package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@TableName("doll")
@Data
public class Doll {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String dname;
    private String dimg1;
    private String dimg2;
    private String dimg3;
    private BigDecimal dprice1;
    private BigDecimal dprice2;
    private String dbrand;
    private String dcolor;
    private String dsize;

}
