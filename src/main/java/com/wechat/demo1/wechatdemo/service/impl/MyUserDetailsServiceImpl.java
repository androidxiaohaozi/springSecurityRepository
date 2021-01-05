package com.wechat.demo1.wechatdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wechat.demo1.wechatdemo.mapper.UsersMapper;
import com.wechat.demo1.wechatdemo.po.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userDetailsService")
public class MyUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        //调用usersmapper方法查询数据库，根据用户名查询

        QueryWrapper<Users> wrapper = new QueryWrapper<>();
        // where username=?
        wrapper.eq("username",s);
        Users users = usersMapper.selectOne(wrapper);

        //判断
        if (users == null) {//数据库没有用户名，认证失败
            throw new UsernameNotFoundException("用户名不存在！");
        }

        List<GrantedAuthority> auths = AuthorityUtils.
                commaSeparatedStringToAuthorityList("admins,ROLE_sale");
        //从查询数据库users对象中获得用户名和密码返回。
        return new User(users.getUsername(),new BCryptPasswordEncoder().encode(users.getPassword()),auths);
    }
}
