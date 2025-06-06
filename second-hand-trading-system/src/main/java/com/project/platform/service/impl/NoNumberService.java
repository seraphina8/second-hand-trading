package com.project.platform.service.impl;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

@Service
public class NoNumberService {

    private static final String DATE_FORMAT = "yyyyMMddHHmmss";
    private static final int RANDOM_PART_LENGTH = 4; // 随机部分的长度

    // 生成订单号
    public String generateOrderNumber() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        String datePart = sdf.format(new Date()); // 获取当前时间并格式化为字符串

        // 生成随机部分
        Random random = new Random();
        StringBuilder randomPart = new StringBuilder();
        for (int i = 0; i < RANDOM_PART_LENGTH; i++) {
            int number = random.nextInt(10); // 生成0-9之间的随机数
            randomPart.append(number);
        }

        // 合并日期部分和随机部分
        return datePart + randomPart.toString();
    }

    public  String generatePayNumber() {
        return  UUID.randomUUID().toString();
    }
}
