package com.springboot.fplcalculatorserver;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.springboot.fplcalculatorserver.accessingdatajpa.UserRepository;
import com.springboot.fplcalculatorserver.entities.User;
import com.springboot.fplcalculatorserver.models.MyUserDetails;

@SpringBootApplication
public class FplcalculatorServerApplication implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		//userRepository.save(new User("stefan", "123", "Stefan", "Vukasinovic"));
		
		final Optional<User> user = userRepository.findByEmail("stefan");
		/*
		 * user.get().setActive(false); user.get().setName("Stefan");
		 * userRepository.save(user.get());
		 */
		System.out.println("findByUserName: " + user);
		//System.out.println(user.get().getRoles());
		System.out.println(new MyUserDetails(user.get()));
		
	}

	@Autowired
	UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(FplcalculatorServerApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Bean
	public WebClient.Builder getWebClientWebBuilder() {
		return WebClient.builder();
	}
	
	

}
