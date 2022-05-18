package com.cmgzs.api;

import com.cmgzs.api.param.UserParam;
import com.cmgzs.service.impl.JwtAuthServiceImpl;
import com.cmgzs.utils.ResultGenerator;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController
@RequestMapping("/api/user")
public class JwtAuthController {


    @Resource
    private JwtAuthServiceImpl jwtAuthServiceImpl;

    /**
     * 登录接口
     *
     * @param userParam
     * @return
     */
    @PostMapping(value = "/login")
    public Object login(@RequestBody UserParam userParam) {
        String username = userParam.getUsername();
        String password = userParam.getPassword();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return ResultGenerator.genFailResult("用户名密码不能为空");
        }
        try {
            String token = jwtAuthServiceImpl.login(username, password);
            return ResultGenerator.genSuccessResult(new HashMap<>() {{
                put("token", token);
            }});
        } catch (Exception e) {
            return ResultGenerator.genFailResult("用户名或密码错误");
        }
    }




    /**
     * 注册接口
     *
     * @param userParam
     * @return
     */
    @PostMapping(value = "/register")
    public Object register(@RequestBody UserParam userParam) {
        String username = userParam.getUsername();
        String password = userParam.getPassword();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return "用户名密码不能为空";
        }
        jwtAuthServiceImpl.register(userParam);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 退出登录
     *
     * @return
     */
    @PostMapping(value = "/logout")
    public Object logout() {
        jwtAuthServiceImpl.logout();
        return ResultGenerator.genSuccessResult();
    }

}
