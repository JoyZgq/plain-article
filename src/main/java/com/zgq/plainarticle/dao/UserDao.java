package com.zgq.plainarticle.dao;

import com.zgq.plainarticle.bean.User;
import org.apache.ibatis.annotations.*;


import java.util.List;

@Mapper
public interface UserDao {
    //    /**
//     * 通过名字查询用户信息
//     */
//    @Select("SELECT * FROM user WHERE name = #{name}")
//    User findUserByName(@Param("name") String name);

    User findUserByName(String name);
}
