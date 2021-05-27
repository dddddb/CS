package com.ldb.mappers;

import com.ldb.pojo.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
@org.apache.ibatis.annotations.Mapper
public interface UserMapper  {

    User queryByNameAndPass(@Param("username") String username,@Param("password") Integer password);

    boolean addUser(User user);
}
