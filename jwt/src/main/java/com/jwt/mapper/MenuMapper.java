package com.jwt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jwt.entity.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper extends BaseMapper<SysMenu> {

    List<String> getResourcesByRoleKey(@Param("roleKeys") List<String> roleKeys);

    List<String> getResourcesByUsername(@Param("username") String username);
}
