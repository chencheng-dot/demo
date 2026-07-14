package com.example.demo.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Dto.ProductQueryDto;
import com.example.demo.Entity.Product;
import com.example.demo.Mapper.ProductMapper;
import com.example.demo.Service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.List;
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Override
    public Page<Product> pageQuery(ProductQueryDto dto) {
        Page<Product> page = new Page<>(dto.getCurrent(), dto.getSize());
        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<>();

        wrapper.like(StringUtils.hasText(dto.getName()), Product::getProductName, dto.getName());
        wrapper.eq(StringUtils.hasText(dto.getCategory()), Product::getCategory, dto.getCategory());
        wrapper.ge(dto.getMinPrice() != null, Product::getPrice, dto.getMinPrice());
        wrapper.le(dto.getMaxPrice() != null, Product::getPrice, dto.getMaxPrice());
        wrapper.orderByDesc(Product::getCreateTime);

        return this.page(page, wrapper);
    }

    @Override
    public String safeDelete(Long id) {
        Product product = this.getById(id);
        if (product == null) return "商品不存在";

        if (product.getStock() > 0) {
            product.setStatus(0);
            this.updateById(product);
            return "商品库存不为0，已自动下架";
        }

        this.removeById(id);
        return "商品已删除";
    }

    @Override
    public List<Product> getWarningProducts() {
        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Product::getStatus, 1)
                .apply("stock <= warn_stock"); // 这里用数据库列名不变
        return this.list(wrapper);
    }
}