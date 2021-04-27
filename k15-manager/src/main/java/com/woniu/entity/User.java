package com.woniu.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "rbac_user")
public class User {

    private Integer id;
    private String username;
    private String password;
    private String salt;
    private String email;
    private String status;



}