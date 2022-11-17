package bank_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SpringbootBankAppApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringbootBankAppApplication.class, args);
	}

}
