package com.cmgzs.mapper;

import com.cmgzs.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    /**
     * 通过用户名查找账号信息
     *
     * @param username
     * @return
     */
    User getUserByUserName(String username);

    /**
     * 插入用户信息
     *
     * @param user
     * @return
     */
    int insertUser(@Param("user") User user);
}
