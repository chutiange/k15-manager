package com.woniu.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "k15_user")
public class KUser {
    private Integer id;
    private String account;
    private String password;
    private String email;
    private String telphone;
    private String avatar;
    private Date regTime;
    private String status;
    @TableField(exist = false,select = false)
    private String rePassword;


}