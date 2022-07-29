package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@TableName("marry")
@Data
public class Marry {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String mname;
    private String mimg1;
    private String mimg2;
    private String mimg3;
    private BigDecimal mprice1;
    private BigDecimal mprice2;
    private String mbrand;
    private String mcolor;
    private String mfabric;
    private Integer mage;
    private String msize;
    private String msize1;
    private String msize2;
    private String msize3;

}
