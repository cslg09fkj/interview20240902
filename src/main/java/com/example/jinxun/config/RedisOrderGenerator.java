package com.example.jinxun.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class RedisOrderGenerator {

    private static final String ORDER_ID_KEY = "order_id";
    private static final AtomicLong sequence = new AtomicLong(0L);

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @PostConstruct
    public void init() {
        // 初始化 Redis 中的订单号序列
        if (!stringRedisTemplate.hasKey(ORDER_ID_KEY)) {
            stringRedisTemplate.opsForValue().set(ORDER_ID_KEY, "0");
        }
    }

    /**
     * 生成订单号
     *
     * @return 订单号
     */
    public synchronized String generateOrderId() {
        // 获取当前日期
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String datePart = dateFormat.format(new Date());

        // 生成顺序号部分
        long nextSequence = sequence.incrementAndGet();
        if (nextSequence > 9999) {
            sequence.set(0L);
            nextSequence = sequence.incrementAndGet();
        }

        // 更新 Redis 中的订单号序列
        String currentSequence = stringRedisTemplate.opsForValue().get(ORDER_ID_KEY);
        long currentCount = Long.parseLong(currentSequence);
        stringRedisTemplate.opsForValue().set(ORDER_ID_KEY, String.valueOf(currentCount + 1));

        // 组合订单号
        return datePart + String.format("%04d", nextSequence);
    }
}
