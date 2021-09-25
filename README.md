# SpringCloud
Out of the four projects MovieInfo and MovieRating are 2 micro services.
MovieCatelog project calling these services.Wo we need to run the project in the below sequences:-

1. EurekaServer--> Start first  -- http://localhost:8761/
2. Movie Rating -->Micro Services  -- http://localhost:8082/rating/rest
3. Movie Info --> Micro Services  -- http://localhost:8081/movie/12345
4. MovieCatelog --> Calling the Movie rating and movie Info services-- http://localhost:8085/catalog/mahes
