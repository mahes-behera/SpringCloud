package com.mbproject.moviecatelog;

import com.mbproject.model.CatalogItem;
import com.mbproject.model.Movie;
import com.mbproject.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping("/{userid}")
    public List<CatalogItem> getCatalog(@PathVariable("userid") String userid) {

        Rating.UserRating userRating = restTemplate.getForObject("http://eureka-movie-rating-service/rating/users/"+userid, Rating.UserRating.class);

        return userRating.getUserRating().stream().map(rating -> {
            Movie movie = restTemplate.getForObject("http://eureka-movie-info-service/movie/"+rating.getMovieId(), Movie.class);
            return new CatalogItem(movie.getName(),"Its a drama",rating.getRating());
        }).collect(Collectors.toList());
    }
}