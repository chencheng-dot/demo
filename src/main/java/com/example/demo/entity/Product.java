package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("pms_product")
public class Product {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String productName;
    private String category;
    private double price;
    private int stock;
    private int warnStock;
    private int status;
    private int isDeleted;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
