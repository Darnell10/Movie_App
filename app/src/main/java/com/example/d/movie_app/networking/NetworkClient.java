package com.example.d.movie_app.networking;

import android.util.Log;

<<<<<<< HEAD
=======
import io.reactivex.Observable;
>>>>>>> e96c7f076dd8ca61a9facad77b591d700187c219
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkClient {


<<<<<<< HEAD
    public static Retrofit retrofit;

=======
>>>>>>> e96c7f076dd8ca61a9facad77b591d700187c219
    public NetworkClient() {
    }


    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            OkHttpClient okHttpClient = builder.build();

            retrofit = new Retrofit.Builder()
<<<<<<< HEAD
                    .baseUrl(NetworkingInterface.BASE_URL)
=======
                    .baseUrl(NetworkingInterface.API_KEY)
>>>>>>> e96c7f076dd8ca61a9facad77b591d700187c219
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();

<<<<<<< HEAD
            Log.d("GET RETROFIT", "BUILD RETROFIT OBJECT");
        }
        Log.d("GET RETROFIT", retrofit.toString());
=======
            Log.d("GET RETROFIT","BUILD RETROFIT OBJECT");
        }
        Log.d("GET RETROFIT",retrofit.toString());
>>>>>>> e96c7f076dd8ca61a9facad77b591d700187c219

        return retrofit;

    }

<<<<<<< HEAD
=======
//    public Observable getMovies(String apiKey) {
//
//        return null;
//    }
>>>>>>> e96c7f076dd8ca61a9facad77b591d700187c219
}
