package com.itjava.sell.controller;

import com.itjava.sell.bean.ProductCategory;
import com.itjava.sell.bean.ProductInfo;
import com.itjava.sell.service.CategoryService;
import com.itjava.sell.service.ProductService;
import com.itjava.sell.util.ResultVOUtils;
import com.itjava.sell.vo.ProductInfoVO;
import com.itjava.sell.vo.ProductVO;
import com.itjava.sell.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
public class BuyProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/list")
    public ResultVO list(){
        List<ProductInfo> upAll = productService.findUpAll();
        List<Integer> categoryTypeList=upAll.stream().map(e->e.getCategoryType()).collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
        List<ProductVO> productVOList=new ArrayList<>();
        for (ProductCategory productCategory:productCategoryList){
            ProductVO productVO=new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());
            List<ProductInfoVO> productInfoVOList=new ArrayList<ProductInfoVO >();
            for (ProductInfo productInfo:upAll){
                if (productInfo.getCategoryType()==productCategory.getCategoryType()){
                    ProductInfoVO productInfoVO=new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setFoods(productInfoVOList);
            productVOList.add(productVO);
        }

        return ResultVOUtils.success(productVOList);
    }
}
