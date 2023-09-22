package com.system.xysmartassistants.domain.config;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

// 实现UserDetails接口
@Data
public class SecuredUser implements UserDetails {

    private String userId;
    private String userAccount;
    private String userPasswd;
    private Integer userType;
    private String userName;

    // 用户权限
    private Collection<GrantedAuthority> authorities;

    // 权限
    // 用户的权限集， 默认需要添加ROLE_ 前缀
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    // 密码
    // 用户的加密后的密码， 不加密会使用{noop}前缀
    @Override
    public String getPassword() {
        return userPasswd;
    }

    // 用户名
    @Override
    public String getUsername() {
        return userAccount;
    }

    // 以下内容必须为true
    // 帐户未过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 帐户未锁定
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 凭证是否过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 用户是否可用
    @Override
    public boolean isEnabled() {
        return true;
    }
}
