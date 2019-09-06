package com.aliyun.aliyundemo.mongo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document(value = "mongo_user")
public class MongoUser {

    private Long id;

    private String name;

    private Integer age;

    private String desc;
}
