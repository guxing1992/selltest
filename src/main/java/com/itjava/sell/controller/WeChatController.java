package com.itjava.sell.controller;

import com.itjava.sell.enums.ResultEnum;
import com.itjava.sell.exception.SellException;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;
@Controller
@RequestMapping("/wechat")
public class WeChatController {
    @Autowired
    private WxMpService wxMpService;
    Logger log=LoggerFactory.getLogger(this.getClass());
    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl){
        //1.配置

        //2.调用方法

        String url="http://elephant.s1.natapp.cc/sell/wechat/userinfo";
        String resultUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, URLEncoder.encode(returnUrl));

        log.info("【微信网页授权】获取code,result={}",resultUrl);
        return  "redirect:"+resultUrl;
    }
    @GetMapping("/userinfo")
    public String useInfo(@RequestParam("code") String code,@RequestParam("state") String returnUrl){
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken=null;
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.error("【微信网页授权】{}",e);
            throw new SellException(ResultEnum.WX_MP_ERROR.getCode(),e.getError().getErrorMsg());
        }
        String openId = wxMpOAuth2AccessToken.getOpenId();
        return "redirect:"+returnUrl+"?openid="+openId;
    }
}
