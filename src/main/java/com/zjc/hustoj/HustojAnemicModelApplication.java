package com.zjc.hustoj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zjc.hustoj.*.dao")
/**
 * @author david
 */
public class HustojAnemicModelApplication {

    public static void main(String[] args) {
        SpringApplication.run(HustojAnemicModelApplication.class, args);
    }

}
