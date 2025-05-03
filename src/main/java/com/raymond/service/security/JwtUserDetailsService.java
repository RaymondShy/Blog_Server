package com.raymond.service.security;

import com.raymond.domain.system.SystemPermission;
import com.raymond.domain.system.SystemUser;
import com.raymond.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private SystemUserService systemUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名查询用户
        SystemUser systemUser = this.systemUserService.getUserByUsername(username);
        if (systemUser == null) {
            throw new UsernameNotFoundException(username);
        }
        // 根据用户名查询用户对应的权限
        List<SystemPermission> systemPermissionList = this.systemUserService.getUserPermissionByUsername(systemUser.getUserName());
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        systemPermissionList
                .stream().forEach(permission -> {
                    grantedAuthorities.add(new SimpleGrantedAuthority(permission.getUrl()));
                });
        UserDetails details = User.builder().username(systemUser.getUserName())
                .password(systemUser.getPassword())
                .authorities(grantedAuthorities)
                .build();
        return details;
    }
}
