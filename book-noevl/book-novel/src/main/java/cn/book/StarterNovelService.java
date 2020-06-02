package cn.book;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("cn.book.novel.mapper")
public class StarterNovelService {
	public static void main(String[] args) {
		SpringApplication.run(StarterNovelService.class, args);
	}
}
