package com.ldb.service;

import com.ldb.mappers.ReviewMapper;
import com.ldb.pojo.Review;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ReviewService {

    @Resource
    private ReviewMapper reviewMapper;
    //添加评论
    public boolean addReview(String name,String message,String r_m_message){
        return reviewMapper.addReview(name,message,r_m_message);
    }
    //删除评论
    public boolean deleteReview(Integer id){
        return reviewMapper.deleteReview(id);
    }
    //查询评论
    public List<Review> queryAll(){
       return reviewMapper.queryAll();
    }
    //根据name查询
    public List<Review> queryByr_m_message(String message){
        return reviewMapper.queryByr_m_message(message);
    }
}
