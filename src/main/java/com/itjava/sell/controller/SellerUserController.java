package com.itjava.sell.controller;

import com.itjava.sell.bean.SellerInfo;
import com.itjava.sell.config.ProjectConfig;
import com.itjava.sell.constant.CookieConstant;
import com.itjava.sell.constant.RedisConstant;
import com.itjava.sell.enums.ResultEnum;
import com.itjava.sell.service.SellerService;
import com.itjava.sell.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/seller")
public class SellerUserController {
    @Autowired
    private SellerService sellerService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ProjectConfig projectConfig;
    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openid")String openid, Map<String,Object> map, HttpServletResponse response){
        //1.openid与数据库进行匹配
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(openid);
        if (sellerInfo==null){
            map.put("msg",ResultEnum.LOGIN_FAIL);
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }
        //2.设置token至redis
        String token=UUID.randomUUID().toString();
        Integer expire=RedisConstant.EXPIRE;
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX,token),openid,
                expire,TimeUnit.SECONDS);

        //3.设置token至cookie
        CookieUtil.set(response,CookieConstant.TOKEN,token,CookieConstant.EXPIRE);
        return  new ModelAndView("redirect:"+projectConfig.getSell()+"/sell/seller/order/list");
    }
    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,HttpServletResponse response,
                       Map<String,Object> map){
        //1.从cookie中查询
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie!=null){
            //2.清楚redis
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX,cookie.getValue()));

            //3.清楚cookie
            CookieUtil.set(response,CookieConstant.TOKEN,null,0);
        }
        map.put("msg",ResultEnum.LOGOUT_SUCCESS.getMessage());
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("common/success",map);


    }
}
