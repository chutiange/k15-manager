package com.woniu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("k15_lesson")
public class Lesson {
    private Integer id;
    private String title;
    private Integer courseId;
    private Integer duration;
    private String video;
    private String isFree;
    private String status;
}
