package com.ldb.mappers;

import com.ldb.pojo.Review;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReviewMapper {
    //添加评论
    public boolean addReview(@Param("name") String name, @Param("message") String message,
                             @Param("r_m_message") String r_m_message);
    //删除评论
    public boolean deleteReview(Integer id);
    //查询评论
    public List<Review> queryAll();
    //根据name查询
    public List<Review> queryByr_m_message(String message);
}
