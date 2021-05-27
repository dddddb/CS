package com.ldb.pojo;

import lombok.Data;

import javax.persistence.Id;

/**
 * 信息
 */
@Data
public class Message {
    @Id
    private Integer id;
    private String mname;
    private String messages;
    private  String photo;
}
