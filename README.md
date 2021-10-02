# SpringCloud
Below 4 projects cover the features : -
1. Communication and Discovery
   MovieCatelog, Movie Info and Movie Rating MS are communicating with EurekaServer.
   These 3 MS use below annoation and in properties file they provide application name and call to server to connect with EurekaServer (@EnableEurekaServer)
   i. EnableEurekaClient
   ii.Load Balance
   iii. RestTemplate
2. Fault tolerance and Resiliance 
MovieCatelog MS calling Movie Rating and Movie Info services. Incase any one of these two MS are not working then still MovieCatelog will work.
@EnableCircuitBreaker - Use in main class for enable circuit breaker
@HystrixCommand - Use in MS method with by using parameter (fallbackMethod = "getFailBackUserRating")
@HystrixProperties - different properties like timeout, number of request

Use proxy on the class where @HystrixCommand use.
1. EurekaServer--> Start first  -- http://localhost:8761/
2. Movie Rating -->Micro Services  -- http://localhost:8082/rating/rest
3. Movie Info --> Micro Services  -- http://localhost:8081/movie/12345
4. MovieCatelog --> Calling the Movie rating and movie Info services-- http://localhost:8085/catalog/mahes
