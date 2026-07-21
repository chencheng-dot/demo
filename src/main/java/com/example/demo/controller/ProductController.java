package com.example.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.dto.ProductQueryDto;
import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Map<String, Object> delete(@PathVariable Long id, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        String role = (String) request.getAttribute("role");
        if (!"admin".equals(role)) {
            result.put("code", 403);
            result.put("msg", "只有管理员可以删除商品");
            return result;
        }
        result.put("code", 200);
        result.put("msg", productService.safeDelete(id));
        return result;
    }

    @GetMapping("/warning")
    public List<Product> warning() {
        return productService.getWarningProducts();
    }

    @GetMapping("/categories")
    public List<String> categories() {
        return productService.getCategories();
    }

    @PostMapping
    public Map<String, Object> add(@RequestBody Product product, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        String role = (String) request.getAttribute("role");
        if (!"admin".equals(role)) {
            result.put("code", 403);
            result.put("msg", "只有管理员可以新增商品");
            return result;
        }
        try {
            productService.addProduct(product);
            result.put("code", 200);
            result.put("msg", "新增成功");
        } catch (Exception e) {
            result.put("code", 500);
            result.put("msg", e.getMessage());
        }
        return result;
    }

    @PostMapping("/{id}/stock-in")
    public Map<String, Object> stockIn(@PathVariable Long id, @RequestBody Map<String, Integer> body, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        try {
            productService.stockIn(id, body.get("quantity"));
            result.put("code", 200);
            result.put("msg", "入库成功");
        } catch (Exception e) {
            result.put("code", 500);
            result.put("msg", e.getMessage());
        }
        return result;
    }

    @PostMapping("/{id}/stock-out")
    public Map<String, Object> stockOut(@PathVariable Long id, @RequestBody Map<String, Integer> body, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        try {
            productService.stockOut(id, body.get("quantity"));
            result.put("code", 200);
            result.put("msg", "出库成功");
        } catch (Exception e) {
            result.put("code", 500);
            result.put("msg", e.getMessage());
        }
        return result;
    }

    @PutMapping("/{id}/status")
    public Map<String, Object> changeStatus(@PathVariable Long id, @RequestBody Map<String, Integer> body, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        String role = (String) request.getAttribute("role");
        if (!"admin".equals(role)) {
            result.put("code", 403);
            result.put("msg", "只有管理员可以调整上下架");
            return result;
        }
        try {
            productService.changeStatus(id, body.get("status"));
            result.put("code", 200);
            result.put("msg", "状态已更新");
        } catch (Exception e) {
            result.put("code", 500);
            result.put("msg", e.getMessage());
        }
        return result;
    }
}
