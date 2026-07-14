package com.example.demo.dto;

import lombok.Data;

@Data
public class ProductQueryDto {
    private Long current = 1L;
    private Long size = 10L;

    private String name;
    private String category;
    private Double minPrice;
    private Double maxPrice;
}
