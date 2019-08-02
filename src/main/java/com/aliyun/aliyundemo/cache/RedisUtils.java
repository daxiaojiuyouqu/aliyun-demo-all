package com.aliyun.aliyundemo.cache;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    /**
     * 设置key的过期时间
     *
     * @param key  key
     * @param time 过期时间,单位(秒)
     * @return
     */
    public boolean expire(String key, long time) {
        if (time > 0) {
            this.redisTemplate.expire(key, time, TimeUnit.SECONDS);
            return true;
        }
        return false;
    }

    /**
     * 根据key获取过期时间
     *
     * @param key key
     * @return 过期时间, 单位(秒)
     */
    public long getExpire(String key) {
        return this.redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /*************************************String****************************************/

    /**
     * 普通缓存获取
     *
     * @param key key
     * @return 值
     */
    public Object get(String key) {
        return StringUtils.isBlank(key) ? null : this.redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存放入
     *
     * @param key key
     * @param obj 值
     * @return
     */
    public boolean set(String key, Object obj) {
        try {
            this.redisTemplate.opsForValue().set(key, obj);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key   key
     * @param value 值
     * @param time  过期时间,单位(秒)
     * @return
     */
    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}






