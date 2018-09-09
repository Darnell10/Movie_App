package com.example.d.movie_app.networking;

import com.example.d.movie_app.data_models.Movie_Response;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NetworkingInterface {

    //String API_KEY = "05064d0ea0a59b3c717097a5d3851776";

    String BASEURL = "https://api.themoviedb.org/3/movie/550?";

    String API_KEY = "05064d0ea0a59b3c717097a5d3851776";

//    @GET("discover/movie")
//    Observable<Movie_Response> getMovies(String apiKey,  @Query(apiKey) String api_key);

    @GET("discover/movie")
    Observable<Movie_Response> getMovies(@Query(API_KEY) String apiKey, @Query("sort_by") String query);


    @GET("search/movie")
    Observable<Movie_Response> getMoviesBasedOnQuery(@Query(API_KEY) String api_key, @Query("query") String query);

}
