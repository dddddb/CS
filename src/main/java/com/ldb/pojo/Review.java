package com.ldb.pojo;

import lombok.Data;

import javax.persistence.Id;

/**
 * 评论
 */
@Data
public class Review {
    @Id
    private Integer r_id;
    private String r_name;
    private String r_message;
    private String photo;
}
