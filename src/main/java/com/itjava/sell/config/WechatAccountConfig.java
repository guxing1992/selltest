package com.itjava.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "wechat")
@Data
public class WechatAccountConfig {
    private String mpAppId;
    private String mpAppSecret;
    /**
     * 商户号
     */
    private String mchId;

    /**
     * 商户密钥
     */
    private String mchKey;

    /**
     * 商户证书路径
     */
    private String keyPath;

    private String notifyUrl;

    private String openAppId;

    private String openAppSecret;

    private Map<String,String> templateId;
}
