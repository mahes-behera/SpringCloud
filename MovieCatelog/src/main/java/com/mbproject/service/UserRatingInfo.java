package com.mbproject.service;

import com.mbproject.model.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class UserRatingInfo {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFailBackUserRating")
    public Rating.UserRating getUserRating(String userid) {
        return  restTemplate.getForObject("http://eureka-movie-rating-service/rating/users/"+ userid, Rating.UserRating.class);
    }

    public Rating.UserRating getFailBackUserRating(@PathVariable("userid") String userid){
        Rating.UserRating userRating = new Rating.UserRating();
        userRating.setUserRating(Arrays.asList(
                new Rating("0", 0)
        ));
        return  userRating;
    }
}
