package com.yonyou.mapping;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.yonyou.pojo.User;

@Mapper

public interface UserMapper {
    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
    void Insert(User user);//如果是类的话可以直接�?
    @Select("select * from user where token=#{token}")
    User findByToken(@Param("token") String token);//如果不是类的话需要加注解，其中的param（�?�token”）是添加的注解
    @Select("select * from user where name=#{username} and password = #{password}")
    User findByUserName(@Param("username") String username,@Param("password") String password);//如果不是类的话需要加注解，其中的param（�?�token”）是添加的注解
}
