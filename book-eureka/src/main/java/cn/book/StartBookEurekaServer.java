package cn.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * book系统注册中心
 * @author tarena
 *
 */
@SpringBootApplication
@EnableEurekaServer
public class StartBookEurekaServer {
	public static void main(String[] args) {
		SpringApplication.run(StartBookEurekaServer.class, args);
	}
}
