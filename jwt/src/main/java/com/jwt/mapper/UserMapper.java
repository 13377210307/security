package com.jwt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jwt.entity.SysUser;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<SysUser> {

    //根据用户名获取用户信息
    SysUser getUserInfoByUsername(@Param("username") String username);
}
