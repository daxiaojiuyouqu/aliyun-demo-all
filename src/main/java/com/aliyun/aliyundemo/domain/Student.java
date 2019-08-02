package com.aliyun.aliyundemo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
public class Student implements Serializable {

    private static final long serialVersionUID = 5878608011944858817L;

    private Long id;

    private Integer num;

    private String name;

    private Integer age;

    @JsonFormat(pattern = "yyyy-MM-dd MM:mm:ss", timezone = "GTM+8")
    private Date createdTime;

    @JsonFormat(pattern = "yyyy-MM-dd MM:mm:ss", timezone = "GTM+8")
    private Date updatedTime;


}