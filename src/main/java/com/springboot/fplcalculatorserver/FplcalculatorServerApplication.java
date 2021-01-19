package com.springboot.fplcalculatorserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import com.springboot.fplcalculatorserver.accessingdatajpa.ProjectRepository;
import com.springboot.fplcalculatorserver.accessingdatajpa.RoleRepository;
import com.springboot.fplcalculatorserver.accessingdatajpa.UserDetailsRepository;
import com.springboot.fplcalculatorserver.accessingdatajpa.UserRepository;

@SpringBootApplication
//@EnableCaching
public class FplcalculatorServerApplication implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
	}

	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserDetailsRepository userDetailsRepository;
	@Autowired
	ProjectRepository projectRepository;
	
	
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
	
	@Bean
    public CacheManager alternateCacheManager() {
        return new ConcurrentMapCacheManager();
    }

}
