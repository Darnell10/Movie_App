package com.example.d.movie_app.networking;

import android.util.Log;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkClient {

    public static Retrofit retrofit;

    public NetworkClient() {
    }


    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            OkHttpClient okHttpClient = builder.build();

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/3/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();

            Log.d("GET RETROFIT","BUILD RETROFIT OBJECT");
        }
        Log.d("GET RETROFIT",retrofit.toString());

        return retrofit;

    }

//    public Observable getMovies(String apiKey) {
//
//        return null;
//    }
}
