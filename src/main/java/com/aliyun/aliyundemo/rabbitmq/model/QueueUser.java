package com.aliyun.aliyundemo.rabbitmq.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
/**
 * 需要实现序列化接口
 */
public class QueueUser implements Serializable {

    private static final long serialVersionUID = 2548670715423371000L;

    private Long id;

    private String name;

    private Integer age;

    private String desc;

    public QueueUser(Long id, String name, Integer age, String desc) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.desc = desc;
    }
}
