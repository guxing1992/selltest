package com.itjava.sell.controller;

import com.itjava.sell.bean.ProductCategory;
import com.itjava.sell.bean.ProductInfo;
import com.itjava.sell.dta.OrderDTO;
import com.itjava.sell.exception.SellException;
import com.itjava.sell.form.ProductForm;
import com.itjava.sell.service.CategoryService;
import com.itjava.sell.service.ProductService;
import com.itjava.sell.util.KeyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Controller
@RequestMapping("/seller/product")
public class SellerProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page, @RequestParam(value = "size",defaultValue = "15") Integer size,
                             Map<String,Object> map){
        Pageable pageable=PageRequest.of(page-1,size);
        Page<ProductInfo> productInfoPage = productService.findAllByPage(pageable);
        map.put("productInfoPage",productInfoPage);
        map.put("currentPage",page);
        map.put("size",size);
        return new ModelAndView("product/list",map);
    }
    @RequestMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId") String productId,Map<String,Object> map){
        try {
            productService.onSale(productId);
        }catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("common/error");
        }
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("common/success",map);
    }
    @RequestMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId,Map<String,Object> map){
        try {
            productService.offSale(productId);
        }catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("common/error");
        }
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("common/success",map);
    }
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId",required = false) String productId,Map<String,Object> map){
        if (!StringUtils.isEmpty(productId)){
            ProductInfo productInfo = productService.findOne(productId);
            map.put("productInfo",productInfo);

        }
        //查询所有类目
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList",categoryList);
        return  new ModelAndView("product/index",map);

    }

    /**
     * 保存和更新product
     * @param form
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
//    @CachePut(cacheNames = "product",key = "123")
    @CacheEvict(cacheNames = "product",key = "123")//在访问这个方法后驱逐缓存
    public ModelAndView save(@Valid ProductForm form, BindingResult bindingResult,Map<String,Object> map){
        if (bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/product/index");
            return new ModelAndView("common/error",map);
        }
        ProductInfo productInfo=new ProductInfo();
        try {
            //如果不为空，说明是修改
            if (!StringUtils.isEmpty(form.getProductId())){
                productInfo= productService.findOne(form.getProductId());
            }else {
                form.setProductId(KeyUtils.genUniqueKey());
            }
            BeanUtils.copyProperties(form,productInfo);
            productService.save(productInfo);
        }catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/index");
            return new ModelAndView("common/error",map);
        }
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("common/success",map);
    }
}
