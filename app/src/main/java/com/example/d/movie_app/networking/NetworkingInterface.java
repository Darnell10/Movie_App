package com.example.d.movie_app.networking;

import com.example.d.movie_app.data_models.Movie_Response;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NetworkingInterface {

    //String API_KEY = "05064d0ea0a59b3c717097a5d3851776";

<<<<<<< HEAD
    String BASE_URL = "https://api.themoviedb.org/3/";

    String API_KEY = "05064d0ea0a59b3c717097a5d3851776";


    @GET("discover/movie")
    Observable<Movie_Response> getMovies(@Query("api_key") String apiKey, @Query("sort_by") String query);


    @GET("search/movie")
    Observable<Movie_Response> getMoviesBasedOnQuery(@Query("api_key") String api_key, @Query("query") String query);
=======
    String API_KEY = "https://api.themoviedb.org/3/movie/550?api_key=05064d0ea0a59b3c717097a5d3851776";

//    @GET("discover/movie")
//    Observable<Movie_Response> getMovies(String apiKey,  @Query(apiKey) String api_key);

    @GET("discover/movie")
    Observable<Movie_Response> getMovies(@Query(API_KEY) String apiKey, @Query("sort_by") String query);


    @GET("search/movie")
    Observable<Movie_Response> getMoviesBasedOnQuery(@Query(API_KEY) String api_key, @Query("query") String query);
>>>>>>> e96c7f076dd8ca61a9facad77b591d700187c219

}
