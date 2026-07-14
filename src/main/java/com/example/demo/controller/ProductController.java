package com.example.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.dto.ProductQueryDto;
import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/page")
    public Page<Product> page(ProductQueryDto dto) {
        return productService.pageQuery(dto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return productService.safeDelete(id);
    }

    @GetMapping("/warning")
    public List<Product> warning() {
        return productService.getWarningProducts();
    }

    @GetMapping("/categories")
    public List<String> categories() {
        return productService.getCategories();
    }
}
