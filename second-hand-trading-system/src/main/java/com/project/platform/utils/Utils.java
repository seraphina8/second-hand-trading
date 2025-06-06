package com.project.platform.utils;

import cn.hutool.crypto.SecureUtil;
import com.project.platform.dto.SysUserDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.function.Consumer;

public class Utils {

    private static char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /**
     * 自定义简单生成盐，是一个随机生成的长度为16的字符串，每一个字符是随机的十六进制字符
     */
    public static String salt() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(16);
        for (int i = 0; i < sb.capacity(); i++) {
            sb.append(hex[random.nextInt(16)]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String salt = salt();
        System.out.println(salt);
        //2e400f7fe59147f895338b4ee9011239
        System.out.println(SecureUtil.md5("getToken" + salt));
    }


    public static SysUserDTO getUser() {
        SysUserDTO currentUser = UserThreadLocal.getCurrentUser();
        return currentUser;

    }

    public static void buildQueryWrapper(Map<String, Object> query, String key, Consumer<? super String> consumer) {
        Optional.ofNullable(query.get(key))
                .map(Object::toString) // 添加这一步转换为字符串，然后再过滤
                .filter(StringUtils::isNotEmpty) // 使用方法引用简化表达
                .ifPresent(consumer);
    }

}

