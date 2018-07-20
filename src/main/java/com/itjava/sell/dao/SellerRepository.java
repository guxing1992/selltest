package com.itjava.sell.dao;

import com.itjava.sell.bean.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<SellerInfo,String> {
    SellerInfo findByOpenid(String sellerId);

}
