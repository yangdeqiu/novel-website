package cn.book;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("cn.book.search.mapper")
public class StarterSearchService {
	public static void main(String[] args) {
		SpringApplication.run(StarterSearchService.class, args);
	}
}
