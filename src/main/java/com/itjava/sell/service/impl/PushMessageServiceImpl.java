package com.itjava.sell.service.impl;

import com.itjava.sell.config.WechatAccountConfig;
import com.itjava.sell.dta.OrderDTO;
import com.itjava.sell.service.PushMessageService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class PushMessageServiceImpl implements PushMessageService {
    @Autowired
    private WxMpService wxMpService;
    @Autowired
    private WechatAccountConfig wechatAccountConfig;
    @Override
    public void orderStatus(OrderDTO orderDTO) {
        WxMpTemplateMessage templateMessage=new WxMpTemplateMessage();
        templateMessage.setTemplateId(wechatAccountConfig.getTemplateId().get("orderStatus"));
        templateMessage.setToUser(orderDTO.getBuyerOpenid());
        List<WxMpTemplateData> data= Arrays.asList(
            new WxMpTemplateData("first","亲记得收货"),
            new WxMpTemplateData("keyword1","微信点餐"),
            new WxMpTemplateData("keyword2","18782938844"),
            new WxMpTemplateData("keyword3",orderDTO.getOrderId()),
            new WxMpTemplateData("keyword4",orderDTO.getOrderStatusEnum().getMsg()),
            new WxMpTemplateData("keyword5","Y"+orderDTO.getOrderAmount()),
            new WxMpTemplateData("remark","欢迎再次光临")
        );
        templateMessage.setData(data);
        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {
            log.error("【微信模板消息】发生失败，{}",e);
            e.printStackTrace();
        }
    }
}
