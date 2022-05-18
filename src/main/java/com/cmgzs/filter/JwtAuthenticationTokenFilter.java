package com.cmgzs.filter;

import com.cmgzs.commons.RedisKeys;
import com.cmgzs.service.impl.myUserDetailsServiceImpl;
import com.cmgzs.utils.JwtTokenUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT验证过滤器
 * 判断当前请求是否携带token、是否过期，是否合法。
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Resource
    myUserDetailsServiceImpl myUserDetailsService;
    @Resource
    JwtTokenUtil jwtTokenUtil;
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        //从请求头中获取token
        String jwtToken = request.getHeader(jwtTokenUtil.getHeader());
        //token判空
        if (jwtToken != null && !StringUtils.isEmpty(jwtToken)) {
            //获取用户姓名
            String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            //如果可以正确的从JWT中提取用户信息，并且该用户未被授权
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
                //检验token的合法性
                if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                    // 检查当前token是否与缓存中的相同，如果不相同，则不做认证，需要重新登陆获取token或使用最新的token。
                    String token = redisTemplate.opsForValue().get(RedisKeys.UID_ + username);
                    if (jwtToken.equals(token)) {
                        //给使用该JWT令牌的用户进行授权
                        UsernamePasswordAuthenticationToken authenticationToken
                                = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        //放入spring security的上下文环境中，表示认证通过
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    } else {
                        //token失效
                        request.setAttribute("ExMessage", "当前认证信息已失效，请重新登录");
                    }
                }
            }
        }
        //过滤器链往后继续执行
        filterChain.doFilter(request, response);
    }
}
