package sgsits.cse.dis.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
@EnableSwagger2
public class InfrastructureApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfrastructureApplication.class, args);
	}
}
