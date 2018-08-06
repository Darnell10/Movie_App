package com.example.d.movie_app.ui;

import android.util.Log;

import com.example.d.movie_app.data_models.Movie_Response;
import com.example.d.movie_app.networking.NetworkClient;
import com.example.d.movie_app.networking.NetworkingInterface;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements MainPresenterInterface {

  //  private final String myApiKey ="05064d0ea0a59b3c717097a5d3851776 ";

    MainViewInterface mvi;

    private String TAG = "Main Presenter";

    public MainPresenter(MainViewInterface mvi){
        this.mvi = mvi;
    }

    @Override
    public void getMovies() {

    }

    public Observable<Movie_Response> getObservable(){
        return NetworkClient.getRetrofit().create(NetworkingInterface.class)
                .getMovies(NetworkingInterface.apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<Movie_Response> getObserver(){
        return new DisposableObserver<Movie_Response>() {

            @Override
            public void onNext(Movie_Response movie_response) {
                Log.d(TAG,"OnNext" + movie_response.getTotalResults());
                mvi.movieDisplay(movie_response);

            }

            @Override
            public void onError(Throwable e) {

                Log.e(TAG, "Error" + e);
                e.printStackTrace();
                mvi.movieError("Error getting movie data");
            }

            @Override
            public void onComplete() {

                Log.d(TAG, "Completed");
            }
        };
    }
}
