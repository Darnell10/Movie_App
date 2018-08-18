package com.example.d.movie_app.networking;

import com.example.d.movie_app.data_models.Movie_Response;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetworkingInterface {

    String apiKey = "05064d0ea0a59b3c717097a5d3851776";

    @GET("discover/movie")
    io.reactivex.Observable<Movie_Response> getMovies(String apiKey, @Query(apiKey) String api_key);

    @GET("search/movie")
    io.reactivex.Observable<Movie_Response> getMoviesBasedOnQuery(@Query(apiKey) String api_key, @Query("query") String query);

}
