package com.woniu.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

/**
 * @Author Administrator
 * @Date 2021/4/21 11:40
 */
@Data
@TableName("rbac_perms")
public class Perm {
    private Integer id;
    private String name;
    private String code;
    private String link;
    private Integer parentid;
    private String type;
    private String status;
    private String percode;
    @TableField(exist = false)
    List<Perm> children;
}
