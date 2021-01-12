package com.zgq.plainarticle;

import com.zgq.plainarticle.bean.User;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zgq.plainarticle.dao")
public class PlainArticleApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlainArticleApplication.class, args);
    }

}
