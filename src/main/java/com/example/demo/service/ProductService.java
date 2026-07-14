package com.example.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.dto.ProductQueryDto;
import com.example.demo.entity.Product;
import java.util.List;

public interface ProductService extends IService<Product> {
    Page<Product> pageQuery(ProductQueryDto dto);
    String safeDelete(Long id);
    List<Product> getWarningProducts();
    List<String> getCategories();
    void addProduct(Product product);
    void stockIn(Long id, Integer quantity);
    void stockOut(Long id, Integer quantity);
    void changeStatus(Long id, Integer status);
}
