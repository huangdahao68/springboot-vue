package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@TableName("xiezhen")
@Data
public class XieZhen {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String xname;
    private String ximg1;
    private String ximg2;
    private String ximg3;
    private BigDecimal xprice1;
    private BigDecimal xprice2;
    private String xbrand;
    private String xcolor;
    private String xsize;

}
