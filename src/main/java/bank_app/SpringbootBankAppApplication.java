package bank_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SpringbootBankAppApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringbootBankAppApplication.class, args);

		/*
		InetAddress ip;
		String hostname;
		try {
			ip = InetAddress.getLocalHost();
			hostname = ip.getHostName();
			System.out.println("Your current IP address : " + ip);
			System.out.println("Your current Hostname : " + hostname);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		 */

	}

}
