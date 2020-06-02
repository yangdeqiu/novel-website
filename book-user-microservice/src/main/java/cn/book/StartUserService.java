package cn.book;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 用户相关系统
 * @author tarena
 *
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("cn.book.user.mapper")
public class StartUserService {
	public static void main(String[] args) {
		SpringApplication.run(StartUserService.class, args);
	}
}
