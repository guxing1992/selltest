package com.itjava.sell.service.impl;

import com.itjava.sell.bean.ProductCategory;
import com.itjava.sell.dao.ProductCategoryDao;
import com.itjava.sell.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    ProductCategoryDao productCategoryDao;
    @Override
    public ProductCategory findOne(Integer categoryId) {
        return productCategoryDao.findById(categoryId).get();
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryDao.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> cateGoryTypeList) {
        return productCategoryDao.findByCategoryTypeIn(cateGoryTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryDao.save(productCategory);
    }
}
