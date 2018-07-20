package com.itjava.sell.service;

import com.itjava.sell.bean.SellerInfo;

public interface SellerService {
    SellerInfo findSellerInfoByOpenid(String openId);
}
