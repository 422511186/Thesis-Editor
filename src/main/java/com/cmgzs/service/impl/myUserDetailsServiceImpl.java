package com.cmgzs.service.impl;


import com.cmgzs.mapper.UserMapper;
import com.cmgzs.pojo.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class myUserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    private GrantedAuthority DEFAULT_ROLE = new SimpleGrantedAuthority("USER");

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userMapper.getUserByUserName(username);
        if (user == null){
            throw new UsernameNotFoundException("用户名不存在！");
        }

        // 2. 设置角色
//        List<GrantedAuthority> auths = new ArrayList<>();
//        String dbRole = userFromDatabase.getRole();
//        if (StringUtils.isNullOrEmpty(dbRole)) {
//            auths.add(DEFAULT_ROLE);
//        } else {
//            String[] roles = dbRole.split(",");
//            for (String r : roles) {
//                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(r);
//                auths.add(grantedAuthority);
//            }
//        }

        List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList("USER");
        return new org.springframework.security.core.userdetails.User(user.getUserName(),
                new BCryptPasswordEncoder().encode(user.getPassWord()), auths);
    }


}
