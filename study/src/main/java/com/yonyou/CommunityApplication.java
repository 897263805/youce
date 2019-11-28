package com.yonyou;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.yonyou.pojo.FileProperties;



@SpringBootApplication
@MapperScan({"com.yonyou.controller","com.yonyou.pojo"})
@EnableConfigurationProperties({
	FileProperties.class
})
public class CommunityApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommunityApplication.class, args);
	}

}
