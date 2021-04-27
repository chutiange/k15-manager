package com.woniu;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @Author Administrator
 * @Date 2021/4/15 15:28
 */
@SpringBootApplication
@MapperScan("com.woniu.dao")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
    @Bean
    public PaginationInterceptor getPaginationInterceptor(){
        return new PaginationInterceptor();
    }
}
