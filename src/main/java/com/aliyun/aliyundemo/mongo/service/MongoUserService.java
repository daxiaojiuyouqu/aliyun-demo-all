package com.aliyun.aliyundemo.mongo.service;

import com.aliyun.aliyundemo.mongo.model.MongoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MongoUserService {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 单条增加
     *
     * @param user
     */
    public void save(MongoUser user) {
        this.mongoTemplate.save(user);
    }

    /**
     * 批量增加
     *
     * @param users
     */
    public void saveBatch(List<MongoUser> users) {
        this.mongoTemplate.insert(users, MongoUser.class);
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    public MongoUser queryById(Long id) {
        Query query = new Query(Criteria.where("id").is(id));
        return this.mongoTemplate.findOne(query, MongoUser.class);
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    public MongoUser findById(Long id) {
        return this.mongoTemplate.findById(id, MongoUser.class);
    }

    /**
     * 查询所有数据
     *
     * @return
     */
    public List<MongoUser> list() {
        return this.mongoTemplate.findAll(MongoUser.class);
    }

    /**
     * 更新数据
     *
     * @param user
     */
    public void update(MongoUser user) {
        Query query = new Query(Criteria.where("id").is(1));
        Update update = new Update().set("age", user.getAge());
        //更新->查询到结果集的第一条
        this.mongoTemplate.updateFirst(query, update, MongoUser.class);
//        //更新->查询到的所有结果集
//        this.mongoTemplate.updateMulti(query,update,MongoUser.class);
    }

    /**
     * 删除数据
     *
     * @param id
     */
    public void delete(Long id) {
        Query query = new Query(Criteria.where("id").is(id));
        this.mongoTemplate.remove(query, MongoUser.class);
    }


}
