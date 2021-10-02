package com.mbproject.service;

import com.mbproject.model.CatalogItem;
import com.mbproject.model.Movie;
import com.mbproject.model.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class MovieInfo {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFailBackCatalogItem")
    public CatalogItem getCatalogItem(Rating rating) {
        Movie movie = restTemplate.getForObject("http://eureka-movie-info-service/movie/" + rating.getMovieId(), Movie.class);
        return new CatalogItem(movie.getName(), "Its a drama", rating.getRating());
    }

    public CatalogItem getFailBackCatalogItem(Rating rating) {
        return new CatalogItem("Movie Name not found", "", rating.getRating());
    }
}
