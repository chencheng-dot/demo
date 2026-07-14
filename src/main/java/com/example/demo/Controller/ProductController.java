package com.example.demo.Controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.Dto.ProductQueryDto;
import com.example.demo.Entity.Product;
import com.example.demo.Service.Impl.ProductServiceImpl;
import com.example.demo.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    // 1. 分页+动态多条件查询
    @GetMapping("/page")
    public Page<Product> page(ProductQueryDto dto) {
        return productService.pageQuery(dto);
    }
    // 2. 安全删除/自动下架
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return productService.safeDelete(id);
    }
    // 3. 低库存预警列表
    @GetMapping("/warning")
    public List<Product> warning() {
        return productService.getWarningProducts();
    }
}