package com.yonyou.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yonyou.mapping.UserMapper;
import com.yonyou.pojo.User;

@Service
public class UserService {
	
	@Autowired
	UserMapper userMapper;
	
	public User findUserByName(String name,String password) {
		
		return userMapper.findByUserName(name,password);
	}
	

}
