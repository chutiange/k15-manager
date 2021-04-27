package com.woniu.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("k15_grade")
public class Grade {

    private Integer id;
    private String name;
    private Integer courseNum;
}
