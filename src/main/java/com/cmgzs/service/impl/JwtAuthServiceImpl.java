package com.cmgzs.service.impl;

import com.cmgzs.api.param.UserParam;
import com.cmgzs.commons.RedisKeys;
import com.cmgzs.exception.UserNameExistedException;
import com.cmgzs.mapper.UserMapper;
import com.cmgzs.pojo.User;
import com.cmgzs.service.JwtAuthService;
import com.cmgzs.utils.JwtTokenUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional(rollbackFor = Exception.class)
public class JwtAuthServiceImpl implements JwtAuthService {

    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private JwtTokenUtil jwtTokenUtil;
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    @Resource
    private UserMapper userMapper;

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return 返回token
     * @throws Exception
     */
    public String login(String username, String password) throws Exception {

        //使用用户名密码进行登录验证
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        //认证
        Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //生成JWT
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        String token = jwtTokenUtil.generateToken(userDetails);
        //TODO 将生成的token放入缓存中
        redisTemplate.opsForValue().set(RedisKeys.UID_ + username, token);
        return token;

    }

    /**
     * 更新token
     * 1、先验证传入的token是否过期，没有过期，则可以更换。
     *
     * @param oldToken
     * @return 新的token
     */
    public String refreshToken(String oldToken) {
        if (!jwtTokenUtil.isTokenExpired(oldToken)) {
            String token = jwtTokenUtil.refreshToken(oldToken);
            //TODO 刷新缓存中的token的值
            String username = SecurityContextHolder.getContext().getAuthentication().getName();

            redisTemplate.opsForValue().set(RedisKeys.UID_ + username, token);
        }
        return null;
    }

    /**
     * 注册功能
     *
     * @param userParam
     */
    @Override
    public void register(UserParam userParam) {
        User user = new User();
        user.setUserName(userParam.getUsername());
        user.setPassWord(userParam.getPassword());
        User userByUserName = userMapper.getUserByUserName(userParam.getUsername());
        //如果当前用户名已存在
        if (userByUserName != null)
            throw new UserNameExistedException();
        System.out.println(user);
        userMapper.insertUser(user);
        System.out.println(user);
    }

    /**
     * 退出登录
     */
    public void logout() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        //清除上下文容器，返回成功消息
        SecurityContextHolder.clearContext();
        //TODO 删除缓存中的token，使其失效
        redisTemplate.opsForValue().getAndDelete(RedisKeys.UID_ + username);
    }
}
