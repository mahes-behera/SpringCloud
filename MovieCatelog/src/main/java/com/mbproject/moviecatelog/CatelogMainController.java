package com.mbproject.MovieCatelog;

import com.mbproject.model.CatalogItem;
import com.mbproject.model.Movie;
import com.mbproject.model.Rating;
import com.mbproject.service.MovieInfo;
import com.mbproject.service.UserRatingInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/catalog")
public class CatelogMainController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MovieInfo movieInfo;

    @Autowired
    private UserRatingInfo userRatingInfo;

    @RequestMapping("/{userid}")
    public List<CatalogItem> getCatalog(@PathVariable("userid") String userid) {

        Rating.UserRating userRating = userRatingInfo.getUserRating(userid);

        return userRating.getUserRating().stream().map(rating -> movieInfo.getCatalogItem(rating)
        ).collect(Collectors.toList());
    }
}