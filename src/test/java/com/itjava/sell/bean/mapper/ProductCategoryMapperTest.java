package com.itjava.sell.bean.mapper;

import com.itjava.sell.bean.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ProductCategoryMapperTest {
    @Autowired
    private ProductCategoryMapper productCategoryMapper;
    @Test
    public void insertByMap() {
        Map<String,Object> map=new HashMap<>();
        map.put("category_name","最不喜欢");
        map.put("category_type",102);
        int i = productCategoryMapper.insertByMap(map);
        Assert.assertEquals(1,i);
    }

    @Test
    public void insertByObject() {

        ProductCategory prodcutCategory=new ProductCategory();
        prodcutCategory.setCategoryName("流行的");
        prodcutCategory.setCategoryType(new Integer(115));
        int result = productCategoryMapper.insertByObject(prodcutCategory);
        Assert.assertEquals(1,result);
    }

    @Test
    public void findByCategoryType() {

        ProductCategory productCategory = productCategoryMapper.findByCategoryType(new Integer(32));
        log.info("productCategory,{}",productCategory);
    }

    @Test
    public void findByCategoryName() {

        ProductCategory productCategory = productCategoryMapper.findByCategoryName("最不喜欢").get(0);
        log.info("productCategory,{}",productCategory);
    }

    @Test
    public void updateByCategoryType() {

        productCategoryMapper.updateByCategoryType("盲目流行的",new Integer(101));
    }

    @Test
    public void updateByCategoryObject() {
        ProductCategory prodcutCategory=new ProductCategory();
        prodcutCategory.setCategoryName("fecsi流行的");
        prodcutCategory.setCategoryType(new Integer(115));
        productCategoryMapper.updateByCategoryObject(prodcutCategory);

    }

    @Test
    public void deleteByCategoryType() {
        productCategoryMapper.deleteByCategoryType(new Integer(115));
    }

    @Test
    public void selectByCategoryType() {
        ProductCategory productCategory = productCategoryMapper.selectByCategoryType(102);
        log.info("productCategory,{}",productCategory);
    }
}