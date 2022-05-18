package com.cmgzs.config;

import com.cmgzs.filter.JwtAuthenticationTokenFilter;
import com.cmgzs.service.impl.myUserDetailsServiceImpl;
import com.cmgzs.utils.Result;
import com.cmgzs.utils.ResultGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder myPasswordEncoder;
    private final myUserDetailsServiceImpl myCustomUserService;
    private final ObjectMapper objectMapper;
    private final JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    //构造函数注入依赖 DI
    public SecurityConfig(ObjectMapper objectMapper, myUserDetailsServiceImpl myCustomUserService,
                          JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter) {
        this.jwtAuthenticationTokenFilter = jwtAuthenticationTokenFilter;
        this.myPasswordEncoder = new BCryptPasswordEncoder();
        this.objectMapper = objectMapper;
        this.myCustomUserService = myCustomUserService;

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 放行所有OPTIONS请求
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .and().httpBasic()
                // 未登录时，进行json格式的提示
                .authenticationEntryPoint((request, response, authException) -> {
                    Object message = request.getAttribute("ExMessage");
                    //检测是否存在异常信息
                    if (message == null || message.toString().isEmpty())
                        message = "未登录";
                    response.setContentType("application/json;charset=utf-8");
//                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    PrintWriter out = response.getWriter();
                    Result result = new Result(401,String.valueOf(message));
                    out.write(objectMapper.writeValueAsString(result));
                    out.flush();
                    out.close();
                })
                .and()
                .authorizeRequests()
                // 处理跨域请求中的Preflight请求
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                //开放登录、注册接口
                .antMatchers("/api/user/login", "/api/user/register").permitAll()
                .and()
                .authorizeRequests()
                .anyRequest().authenticated() // 必须授权才能范围
                .and()

                .exceptionHandling()
                // 没有权限，返回json
                .accessDeniedHandler((request, response, authException) -> {
                    response.setContentType("application/json;charset=utf-8");
//                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    PrintWriter out = response.getWriter();
                    Result result = new Result(403,"权限不足");
                    out.write(objectMapper.writeValueAsString(result));
                    out.flush();
                    out.close();
                })
                .and().cors()
                .and().csrf().disable()
                //禁用session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * 描述: 静态资源放行，这里的放行，是不走 Spring Security 过滤器链
     **/
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/file/**");
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        // 对默认的UserDetailsService进行覆盖
        authenticationProvider.setUserDetailsService(myCustomUserService);
        authenticationProvider.setPasswordEncoder(myPasswordEncoder);
        return authenticationProvider;
    }

}