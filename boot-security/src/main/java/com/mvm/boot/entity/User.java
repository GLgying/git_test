package com.mvm.boot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:TuoTuo
 * @createDate:2022/12/2 19:29
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    private String username;
    private String password;
    private List<Role> roles;
    /**
     * 获取用户名
     */
    @Override
    public String getUsername() {
        return username;
    }
    /**
     * 获取密码
     */
    @Override
    public String getPassword() {
        return password;
    }
    /**
     * 用户的权限集， 默认需要添加ROLE_ 前缀
     */
    @Override
    @JsonIgnore
    public List<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        }
        return authorities;
    }
    /**
     * 账户是否过期
     */
    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }
    /**
     * 账户是否锁定
     */
    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }
    /**
     * 凭证是否过期
     */
    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }
    /**
     * 用户是否可用
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
