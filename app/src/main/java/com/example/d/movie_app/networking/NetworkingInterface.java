package com.example.d.movie_app.networking;

import com.example.d.movie_app.data_models.Movie_Response;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NetworkingInterface {


    String BASE_URL = "https://api.themoviedb.org/3/";

    String API_KEY = "05064d0ea0a59b3c717097a5d3851776";


    @GET("discover/movie")
    Observable<Movie_Response> getMovies(@Query("api_key") String apiKey, @Query("sort_by") String query);


    @GET("search/movie")
    Observable<Movie_Response> getMoviesBasedOnQuery(@Query("api_key") String api_key, @Query("query") String query);


}
