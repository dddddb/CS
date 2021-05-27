package com.ldb.service;

import com.ldb.mappers.UserMapper;
import com.ldb.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public User query(User user){
        return userMapper.queryByNameAndPass(user.getUsername(),user.getPassword());

    }
    public boolean addUser(User user){

        if (query(user)!=null)
            return false;
        else {
            userMapper.addUser(user);
            return true;
        }
    }
}
