package com.itjava.sell.aspect;

import com.itjava.sell.constant.CookieConstant;
import com.itjava.sell.constant.RedisConstant;
import com.itjava.sell.exception.SellException;
import com.itjava.sell.exception.SellerAuthorizeException;
import com.itjava.sell.util.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut("execution(public * com.itjava.sell.controller.Seller*.*(..))"+
    "&& !execution(public * com.itjava.sell.controller.SellerUserController.*(..))")
    public void verify(){

    }
    @Before("verify()")
    public void doVerify(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //查询cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie==null){
            log.warn("【登录校验】cookie中查不到token");
            throw new SellerAuthorizeException();
        }
        //到redis中查询
        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
        if (StringUtils.isEmpty(tokenValue)){
            log.warn("【登录校验】redis中查不到token");
            throw new SellerAuthorizeException();
        }


    }
}
