package com.aliyun.aliyundemo.cache;

import com.aliyun.aliyundemo.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentRedisCacheService {

    @Autowired
    private RedisUtils redisUtils;

    public Student get(Long id) {
        String key = RedisKey.studentKey(id);
        return (Student) redisUtils.get(key);
    }

    public boolean set(Long id, Student student) {
        String key = RedisKey.studentKey(id);
        return this.redisUtils.set(key, student);
    }
}
