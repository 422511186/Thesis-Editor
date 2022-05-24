package com.cmgzs.service;

import com.cmgzs.api.param.UserParam;

public interface JwtAuthService {
    /**
     * 登录
     *
     * @param username
     * @param password
     * @return 返回token
     * @throws Exception
     */
    String login(String username, String password) throws Exception;

    /**
     * 更新token
     * 1、先验证传入的token是否过期，没有过期，则可以更换。
     *
     * @param oldToken
     * @return 新的token
     */
    String refreshToken(String oldToken);

    /**
     * 注册功能
     *
     * @param userParam
     */
    void register(UserParam userParam);

    /**
     * 修改密码
     *
     * @param pwd
     */
    void updatePWD(String pwd);

    /**
     * 退出登录
     */
    void logout();
}
