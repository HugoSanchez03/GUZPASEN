package guzpasen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "guzpasen.models")
public class MyWeb {

	public static void main(String[] args) {
		SpringApplication.run(MyWeb.class, args);
	}

}
