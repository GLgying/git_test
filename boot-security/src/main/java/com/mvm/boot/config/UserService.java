package com.mvm.boot.config;

import com.google.common.collect.Lists;
import com.mvm.boot.entity.Role;
import com.mvm.boot.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:TuoTuo
 * @createDate:2022/12/2 19:32
 * @description:
 */
@Service
@Slf4j
public class UserService implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //TODO 正常应该查询数据库获取用户和用户的权限
//        User user = userMapper.loadUserByUsername(userName);
//        List<Role> roles = rolesMapper.getRolesByUid(user.getId());
//        user.setRoles(roles);
        log.info("登陆用户名： {}", userName);
        //通过用户名查询到的密码 密码肯定是加密过的 这里明文密码是 123456
//        String password = "e10adc3949ba59abbe56e057f20f883e";
        String password = "e10adc3949ba59abbe56e057f20f883e";
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        password = passwordEncoder.encode("123456");
        //用户对应权限
        List<Role> roles;
        if("张三".equals(userName)){
            roles = Lists.newArrayList(new Role(1L, "校长"), new Role(2L, "教师"));
        }else {
            roles = Lists.newArrayList(new Role(2L, "教师"), new Role(3L, "学生"));
        }
        User user = new User(userName, password, roles);
        return user;
    }
}