package com.aliyun.aliyundemo.cache;

import com.aliyun.aliyundemo.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class StudentRedisCacheService {

    @Autowired
    private RedisTemplate redisTemplate;

    public Student redisGet(Long id) {
        String key = RedisKey.studentKey(id);
        return (Student) this.redisTemplate.opsForValue().get(key);
    }

    public void redisSet(Long id,Student student){
        String key = RedisKey.studentKey(id);
        this.redisTemplate.opsForValue().set(key,student);
    }
}
