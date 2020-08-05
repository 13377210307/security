package com.jwt.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class SysUser{

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String username;

    private String password;

}
