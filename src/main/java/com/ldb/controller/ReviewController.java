package com.ldb.controller;

import com.ldb.pojo.ResultInfo;
import com.ldb.pojo.Review;
import com.ldb.service.ReviewService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class ReviewController {

    @Resource
    private ReviewService reviewService;
    //添加评论
    @RequestMapping("addReview")
    public ResultInfo addReview(HttpServletRequest request){
        ResultInfo info = new ResultInfo();
//        System.out.println(review);
        String username = request.getParameter("username");
        String view = request.getParameter("view");
        String viewMessage = request.getParameter("viewMessage");
        boolean b = reviewService.addReview(username, view,viewMessage);
        if (b){
            info.setFlag(true);
            info.setData(view);
            System.out.println(view);
        }else {
            info.setFlag(false);
            info.setErrorMsg("添加失败");
        }
        return info;
    }
    @RequestMapping(value = "addReview2",params = {"r_name","r_message"})
    public String add(Review review){
        System.out.println(review);
        return review.toString();
    }

    //删除评论
    @RequestMapping("deleteReview")
    public ResultInfo deleteReview(HttpServletRequest request){
        ResultInfo info = new ResultInfo();
        String id = request.getParameter("id");
        boolean b = reviewService.deleteReview(Integer.parseInt(id));

        if (b){
            info.setFlag(true);
            info.setData("删除成功");
        }else {
            info.setFlag(false);
            info.setErrorMsg("删除失败");
        }
        return info;
    }
    //查询评论
    @RequestMapping("queryReview")
    public ResultInfo queryReview(){

        ResultInfo info = new ResultInfo();
        List<Review> reviews = reviewService.queryAll();
        if (reviews!=null){
            info.setFlag(true);
            info.setData(reviews);
        }else{
            info.setFlag(false);
            info.setErrorMsg("查询失败");
        }
        return info;
    }
    //根据name查询
    @RequestMapping("queryReviewByr_m_message")
    public ResultInfo queryReviewByr_m_message(HttpServletRequest request){
        ResultInfo info = new ResultInfo();
        String view = request.getParameter("view");
        List<Review> reviews = reviewService.queryByr_m_message(view);
        if (reviews!=null){
            info.setFlag(true);
            info.setData(reviews);
        }else {
            info.setFlag(false);
            info.setErrorMsg("查询失败");
        }
       /* HttpSession session = request.getSession();
        if (session!=null){
            String myView = String.valueOf(session.getAttribute("myView"));
            List<Review> reviews = reviewService.queryByr_m_message(myView);
            if (reviews!=null){
                info.setFlag(true);
                info.setData(reviews);
            }else {
                info.setFlag(false);
                info.setErrorMsg("查询失败");
            }
        }else {
            String view = request.getParameter("view");
            session.setAttribute("myView",view);
        }*/
//        String view = request.getParameter("view");

        return info;
    }
}
