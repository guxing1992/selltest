package com.itjava.sell.service.impl;

import com.itjava.sell.exception.SellException;
import com.itjava.sell.service.RedisLock;
import com.itjava.sell.service.SecKillService;
import com.itjava.sell.util.KeyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class SecKillServiceImpl implements SecKillService {
    private static final long TIMEOUT = 10*1000;
    @Autowired
    private RedisLock redisLock;
    static Map<String,Integer > products;
    static Map<String,Integer> stock;
    static Map<String,String> orders;
    static {
        /**
         * 模拟多个表。商品信息表，库存表，秒杀成功订单表
         */
        products=new HashMap<>();
        stock=new HashMap<>();
        orders=new HashMap<>();
        products.put("123456",100000);
        stock.put("123456",100000);
    }
    private String queryMap(String productId){
        return "国庆活动，皮蛋瘦肉粥特价，限分量"+
                products.get(productId)+
                "还剩："+stock.get(productId)+" 份"
                +" 该商品活动下单用户数目："+orders.size()+" 人";
    }

    @Override
    public String querySecKillProductInfo(String productId) {
        return this.queryMap(productId);
    }

    @Override
    public void orderProductMockDiffUser(String productId) {
        //加锁
        long time=System.currentTimeMillis()+TIMEOUT;
        boolean islock = redisLock.lock(productId, String.valueOf(time));
        if (!islock){
            throw new SellException(101,"哎呦喂，人也太多了，换个姿势再试试");
        }
        //查询该商品库存，为0时活动结束
        int stockNum = stock.get(productId);
        if (stockNum==0){
            throw new SellException(100,"活动结束");
        }else {
            //2.下单（模拟不同用户openid）
            orders.put(KeyUtils.genUniqueKey(),productId);
            //3.减库存
            stockNum=stockNum-1;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stock.put(productId,stockNum);
        }
        //解锁
        redisLock.unlock(productId,String.valueOf(time));
    }
}
