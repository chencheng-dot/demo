package com.example.demo.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.Dto.ProductQueryDto;
import com.example.demo.Entity.Product;
import java.util.List;
public interface ProductService extends IService<Product> {
    // 分页多条件查询
    Page<Product> pageQuery(ProductQueryDto dto);
    // 安全删除/自动下架
    String safeDelete(Long id);
    // 低库存预警列表
    List<Product> getWarningProducts();
}