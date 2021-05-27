package com.ldb.controller;

import com.github.pagehelper.PageHelper;
import com.ldb.pojo.Message;
import com.ldb.pojo.PageBean;
import com.ldb.pojo.ResultInfo;
import com.ldb.service.MessageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
public class MessageController {

    @Resource
    private MessageService messageService;

    @RequestMapping("/addMessage")
    public ResultInfo addMessage(HttpServletRequest request){
        ResultInfo info = new ResultInfo();
        String comment = request.getParameter("comment");
//        System.out.println(comment);
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("username".equals(cookie.getName())){
                String username=cookie.getValue();
                boolean b = messageService.addMessage(username,comment,"赛事");
                if (b){
                    info.setFlag(true);
                    info.setData(comment);
//                    System.out.println(info.getData());
                }else {
                    info.setFlag(false);
                    info.setErrorMsg("添加失败");
                }
            }
        }
        return info;
    }

    @RequestMapping("/queryAll")
    public ResultInfo queryAll(){
        ResultInfo info = new ResultInfo();
        List<Message> list = messageService.queryAll();
        if (list!=null){
            info.setFlag(true);
            info.setData(list);
        }else {
            info.setFlag(false);
            info.setErrorMsg("查询失败");
        }
        return info;

    }

    /**
     * PageHelper 分页
     * @return
     */
    @RequestMapping("/queryAll2")
    public ResultInfo queryAll2(HttpServletRequest request){
        ResultInfo info = new ResultInfo();
        String currentPage = request.getParameter("currentPage");
        String pageSize = request.getParameter("pageSize");
        PageHelper.startPage(Integer.parseInt(currentPage),Integer.parseInt(pageSize));
        List<Message> list = messageService.queryAll();
        if (list!=null){
            info.setFlag(true);
            info.setData(list);
        }else {
            info.setFlag(false);
            info.setErrorMsg("查询失败");
        }
        return info;

    }

    @RequestMapping("/queryById")
    public ResultInfo queryById(HttpServletRequest request){
        ResultInfo info = new ResultInfo();
        String id = request.getParameter("id");

        Message message = messageService.queryById(Integer.parseInt(id));
        if (message!=null){
            info.setFlag(true);
            info.setData(message);
        }else {
            info.setFlag(false);
            info.setErrorMsg("查询失败");
        }
        return info;

    }

    @RequestMapping("/queryByName")
    public ResultInfo queryByName(HttpServletRequest request){
        ResultInfo info = new ResultInfo();
        Cookie[] cookies = request.getCookies();

        if (cookies!=null){
            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())){
                    String username = cookie.getValue();
                    List<Message> list = messageService.queryByName(username);
                    if (list!=null){
                        info.setFlag(true);
                        info.setData(list);
                    }else {
                        info.setFlag(false);
                        info.setErrorMsg("查询失败");
                    }
                }
            }
        }



        return info;

    }

    @RequestMapping("/queryByCategorize")
    public ResultInfo queryBycategorize(HttpServletRequest request){
        ResultInfo info = new ResultInfo();
        String categorize = request.getParameter("categorize");

        List<Message> list = messageService.queryByCategorize(categorize);
        if (list!=null){
            info.setFlag(true);
            info.setData(list);
        }else {
            info.setFlag(false);
            info.setErrorMsg("查询失败");
        }
        return info;
    }

    @RequestMapping("/queryMatch")
    public ResultInfo queryMatch(HttpServletRequest request, HttpServletResponse response) {
        ResultInfo info = new ResultInfo();


        List<Message> list = messageService.queryByCategorize("赛事");
        if (list != null) {
            info.setFlag(true);
            info.setData(list);
        } else {
            info.setFlag(false);
            info.setErrorMsg("查询失败");
        }
        return info;
    }

    @RequestMapping("/queryEntertainment")
    public ResultInfo queryEntertainment(HttpServletRequest request, HttpServletResponse response) {
        ResultInfo info = new ResultInfo();


        List<Message> list = messageService.queryByCategorize("娱乐");
        if (list != null) {
            info.setFlag(true);
            info.setData(list);
        } else {
            info.setFlag(false);
            info.setErrorMsg("查询失败");
        }
        return info;
    }
    @RequestMapping("/delete")
    public ResultInfo delete(HttpServletRequest request){
        ResultInfo info = new ResultInfo();
        String id = request.getParameter("id");

        boolean b = messageService.delete(Integer.parseInt(id));
        if (b){
            info.setFlag(true);
            info.setData("删除成功");
        }else {
            info.setFlag(false);
            info.setErrorMsg("删除失败");
        }
        return info;

    }

    @RequestMapping("/findTotalCount")
    public ResultInfo findTotalCount(HttpServletRequest request){
        ResultInfo info = new ResultInfo();
        String id = request.getParameter("id");
        long totalCount = messageService.findTotalCount(Integer.parseInt(id));
        if (totalCount>0){
            info.setFlag(true);
            info.setData(totalCount);
        }else {
            info.setFlag(false);
            info.setErrorMsg("查询失败");
        }
        return info;

    }
    @RequestMapping("/queryPage")
    public ResultInfo queryPage(HttpServletRequest request){
        ResultInfo info = new ResultInfo();
        String id = request.getParameter("id");
        String currentPage = request.getParameter("currentPage");
        String pageSize = request.getParameter("pageSize");
        PageBean<Message> pb = messageService.queryPage(Integer.parseInt(id),
                Integer.parseInt(currentPage), Integer.parseInt(pageSize));
        if (pb!=null){
            info.setFlag(true);
            info.setData(pb.getList());
        }else {
            info.setFlag(false);
            info.setErrorMsg("查询失败");
        }
        return info;
    }

    @RequestMapping("/categorize")
    public ResultInfo queryCategorize(HttpServletRequest request){
        ResultInfo info = new ResultInfo();
        String categorize = request.getParameter("categorize");

        List<Message> list = messageService.queryCategorize(categorize);

        if (list!=null){
            info.setFlag(true);
            info.setData(list);
        }else {
            info.setFlag(false);
            info.setErrorMsg("查询失败");
        }
        return info;

    }
}
