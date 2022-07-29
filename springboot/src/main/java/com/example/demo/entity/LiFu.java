package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@TableName("lifu")
@Data
public class LiFu {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer uuid;
    private String lname;
    private String limg1;
    private String limg2;
    private String limg3;
    private BigDecimal lprice1;
    private BigDecimal lprice2;
    private String lbrand;
    private String lcolor;
    private String lfabric;
    private Integer lage;
    private String lsize;
    private String lsize1;
    private String lsize2;
    private String lsize3;

}
