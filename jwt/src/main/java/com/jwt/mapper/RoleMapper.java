package com.jwt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jwt.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper extends BaseMapper<SysRole> {

    //根据用户id查询角色
    List<SysRole> getRolesByUserId(@Param("userId") String userId);
}
