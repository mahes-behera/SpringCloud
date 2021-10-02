package com.mbproject;

import com.mbproject.service.MovieInfo;
import com.mbproject.service.UserRatingInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableCircuitBreaker
public class EurekaClientApplication {

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Bean
	public UserRatingInfo getUserRatingInfo(){
		return new UserRatingInfo();
	}

	@Bean
	public MovieInfo getMovieInfo(){
		return new MovieInfo();
	}

	public static void main(String[] args) {
		SpringApplication.run(EurekaClientApplication.class, args);
	}

}
