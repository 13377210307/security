package com.jwt.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class SysMenu implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String name;

    private String url;
}
