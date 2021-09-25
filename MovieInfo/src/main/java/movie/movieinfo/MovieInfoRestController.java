package movie.movieinfo;

import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieInfoRestController {

    @RequestMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId)
        {
            return new Movie(movieId,"Transformer");
        }
}
