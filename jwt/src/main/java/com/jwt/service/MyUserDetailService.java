package com.jwt.service;

import com.jwt.entity.SysRole;
import com.jwt.entity.SysUser;
import com.jwt.mapper.RoleMapper;
import com.jwt.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        SysUser sysUser = this.userMapper.getUserInfoByUsername(name);
        if (sysUser == null) {
            return null;
        }
        //获取用户角色
        String roles = "";
        List<SysRole> sysRoles = this.roleMapper.getRolesByUserId(sysUser.getId());
        if (!CollectionUtils.isEmpty(sysRoles)) {
            roles = sysRoles.stream().map(SysRole::getKey).collect(Collectors.joining());
        }
        return new User(name,sysUser.getPassword(),true,true,true,true, AuthorityUtils.commaSeparatedStringToAuthorityList(roles));
    }
}
