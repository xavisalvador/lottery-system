package org.xaviersalvador.lottery.system;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

	@Bean("channelRestTemplate")
	@Qualifier("channelRestTemplate")
	public RestTemplate channelRestTemplate() {
		return new RestTemplate();
	}

	@Bean("pushNotificationRestTemplate")
	@Qualifier("pushNotificationRestTemplate")
	public RestTemplate pushNotificationRestTemplate() {
		return new RestTemplate();
	}

}
