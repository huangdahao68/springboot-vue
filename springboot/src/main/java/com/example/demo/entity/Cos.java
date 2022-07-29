package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@TableName("cos")
@Data
public class Cos {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String cname;
    private String cimg1;
    private String cimg2;
    private String cimg3;
    private BigDecimal cprice1;
    private BigDecimal cprice2;
    private String cbrand;
    private String ccolor;
    private String cfabric;
    private Integer cage;
    private String csize;
    private String csize1;
    private String csize2;
    private String csize3;

}
