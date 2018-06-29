package com.itjava.sell.service;

import com.itjava.sell.bean.ProductCategory;

import java.util.List;

public interface CategoryService {
    ProductCategory findOne(Integer categoryId);
    List<ProductCategory> findAll();
    List<ProductCategory> findByCategoryTypeIn(List<Integer> cateGoryTypeList);
    ProductCategory save(ProductCategory productCategory);
}
