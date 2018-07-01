package com.itjava.sell.dao;

import com.itjava.sell.bean.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Test
    public void findOneTest(){
        ProductCategory productCategory = productCategoryRepository.findById(1).get();
        System.out.println(productCategory.toString());

    }
    @Test
    public void addAProductCategory(){
        ProductCategory productCategory = productCategoryRepository.findById(2).get();
        productCategory.setCategoryType(15);
        productCategoryRepository.save(productCategory);
    }
    @Test
    public void testfindByCategoryTypeIn(){
        List<ProductCategory> byCategoryTypeIn = productCategoryRepository.findByCategoryTypeIn(Arrays.asList(1, 15));
        System.out.println(byCategoryTypeIn);
    }
}