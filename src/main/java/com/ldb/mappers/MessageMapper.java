package com.ldb.mappers;

import com.ldb.pojo.Message;
import com.ldb.pojo.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MessageMapper {
    //查询所有
    public List<Message> queryAll();
    //根据id查询
    public Message queryById(Integer id);
    //根据名字查询
    public List<Message> queryByName(String mname);

    public List<Message> queryByCategorize(String categorize);
    //添加
    public boolean addMessage(@Param("mname") String mname,@Param("messages") String messages,
                              @Param("categorize") String categorize);

    //删除
    public boolean delete(Integer id);

    //分页
    /**
     * 根据id查询总记录数
     */
    public long findTotalCount(int id);

    /**
     * 根据cid，start,pageSize查询当前页的数据集合
     */
    public List<Message> queryPage(@Param("id")int id, @Param("start")int start,
                                   @Param("pageSize")int pageSize);
    //类别
    public List<Message> queryCategorize(String categorize);
}
