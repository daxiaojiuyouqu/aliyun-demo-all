package com.aliyun.aliyundemo.cache;

import com.aliyun.aliyundemo.constant.RedisKeyPrefix;

public class RedisKey {

    public static String studentKey(Long id) {
        return RedisKeyPrefix.student + id;
    }
}
