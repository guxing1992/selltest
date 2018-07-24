package com.itjava.sell.handler;

import com.itjava.sell.aspect.SellerAuthorizeAspect;
import com.itjava.sell.config.ProjectConfig;
import com.itjava.sell.exception.SellException;
import com.itjava.sell.exception.SellerAuthorizeException;
import com.itjava.sell.util.ResultVOUtils;
import com.itjava.sell.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
@ControllerAdvice
public class SellerExceptionHandler {
    @Autowired
    private ProjectConfig projectConfig;
    //拦截登录异常
    //http://elephant.s1.natapp.cc/sell/wechat/qrAuthorize?returnUrl=http://elephant.s1.natapp.cc/sell/seller/login
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerSellerAuthorizException(){
        return new ModelAndView("redirect:".concat(projectConfig.getSell()).concat("/sell/wechat/qrAuthorize")
                .concat("?returnUrl=").
                        concat(projectConfig.getSell()).
                        concat("/sell/seller/login"));
    }

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handlerSellException(SellException e){
        return ResultVOUtils.error(e.getCode(),e.getMessage());
    }
}
