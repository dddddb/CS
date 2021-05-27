package com.ldb.service;

import com.ldb.mappers.MessageMapper;
import com.ldb.pojo.Message;
import com.ldb.pojo.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MessageService {
    @Resource
    private MessageMapper messageMapper;

    public List<Message> queryAll(){
        return messageMapper.queryAll();
    }

    public Message queryById(Integer id){
        return messageMapper.queryById(id);
    }

    public List<Message> queryByName(String name){
        return messageMapper.queryByName(name);
    }
    public List<Message> queryByCategorize(String categorize){
        return messageMapper.queryByCategorize(categorize);
    }

    public boolean addMessage(String mname,String messages,String categorize){

        return messageMapper.addMessage(mname,messages,categorize);
    }

    public boolean delete(Integer id){
        return messageMapper.delete(id);
    }
    /**
     * 根据id查询总记录数
     */
    public long findTotalCount(int id){
        return  messageMapper.findTotalCount(1);
    }

    /**
     * 根据id，start,pageSize查询当前页的数据集合
     */
    public PageBean<Message> queryPage(int id, int currentPage, int pageSize){
        PageBean<Message> pb = new PageBean<Message>();
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);

        int totalCount = (int) messageMapper.findTotalCount(id);
        pb.setTotalCount(totalCount);
        int start=(currentPage-1)*pageSize;
        List<Message> list =messageMapper.queryPage(id, start, pageSize);
        pb.setList(list);

        int totalPage = totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
        pb.setTotalPage(totalPage);
        return pb;
    }

    public List<Message> queryCategorize(String categorize){
        List<Message> list = messageMapper.queryCategorize(categorize);
        return list;
    }
}
