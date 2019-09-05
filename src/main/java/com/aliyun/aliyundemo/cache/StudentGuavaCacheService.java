package com.aliyun.aliyundemo.cache;

import com.aliyun.aliyundemo.dao.StudentMapper;
import com.aliyun.aliyundemo.domain.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class StudentGuavaCacheService {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StudentMapper studentMapper;

    /**
     * 缓存参数说明:
     * value表示缓存名称,key表示缓存的key,
     * condition表示需要满足的缓存的条件,unless表示否决缓存条件
     *
     * @param id
     * @return
     */
    @Cacheable(value = "student", key = "#id", condition = "#id!=null", unless = "#result==null")
    public Student getByPk(Long id) {
        LOGGER.info("开始查询");
        return this.studentMapper.selectByPrimaryKey(id);

    }

    /**
     * allEntries是否移除所有数据,true表示全部移除
     */
    @CacheEvict(value = "student", allEntries = true)
    public void deleteAll() {
        LOGGER.info("准备删除所有学生数据");
        this.studentMapper.deleteAll();
    }

    @CacheEvict(value = "student", key = "#id", condition = "#id!=null")
    public void deleteByPk(Long id) {
        LOGGER.info("删除某个学生数据");
        this.studentMapper.deleteByPrimaryKey(id);
    }

    @CachePut(value = "student", key = "#student.id")
    public Student update(Student student) {
        this.studentMapper.updateByPrimaryKey(student);
        return student;
    }

}
