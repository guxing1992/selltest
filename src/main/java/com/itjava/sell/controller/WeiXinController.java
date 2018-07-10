package com.itjava.sell.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/weixin")
public class WeiXinController {
    Logger log=LoggerFactory.getLogger(WeiXinController.class);
    @GetMapping("/auth")
    public void auth(@RequestParam(name = "code",required = false) String code){
        log.info("进入auth方法。。。");
        log.info("code={}",code);

        RestTemplate restTemplate = new RestTemplate();
        String uri="https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxea9e40a46a00afe9&secret=e28549e1739a1b01fb5b697f967c3405&code="+code+"&grant_type=authorization_code";
        String response = restTemplate.getForObject(uri, String.class);
        log.info("response={}",response);
    }
}
