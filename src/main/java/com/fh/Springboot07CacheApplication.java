package com.fh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@MapperScan("com.fh.mapper")
@SpringBootApplication
@EnableCaching //开启缓存
public class Springboot07CacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot07CacheApplication.class, args);
	}

}
