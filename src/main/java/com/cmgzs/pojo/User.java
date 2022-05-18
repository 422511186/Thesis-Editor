package com.cmgzs.pojo;

import lombok.Data;

/**
 * 用户实体类
 */
@Data
public class User {
    //    主键
    private int id;
    //    用户名
    private String userName;
    //    密码
    private String passWord;
}
