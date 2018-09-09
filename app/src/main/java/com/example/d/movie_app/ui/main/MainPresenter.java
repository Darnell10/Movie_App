package com.example.d.movie_app.ui.main;

import android.util.Log;

import com.example.d.movie_app.data_models.Movie_Response;
import com.example.d.movie_app.networking.NetworkClient;
import com.example.d.movie_app.networking.NetworkingInterface;

import org.reactivestreams.Subscriber;

import java.net.NetworkInterface;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements MainPresenterInterface {

    NetworkClient networkClient;

    MainViewInterface mainViewInterface;

    private String TAG = "MainPresenter";

    public MainPresenter(MainViewInterface mainViewInterface) {
        this.mainViewInterface = mainViewInterface;
    }


    @Override
    public void getMovies() {

        getObservable().subscribeWith(getObserver());

    }


    public Observable<Movie_Response> getObservable(){
        return NetworkClient.getRetrofit()
                .create(NetworkClient.class)
                .getMovies(NetworkingInterface.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }



    public DisposableObserver<Movie_Response> getObserver(){
        return new DisposableObserver<Movie_Response>() {

            @Override
            public void onNext( @NonNull Movie_Response movie_response) {
                Log.d(TAG,"OnNext" + movie_response.getTotalResults());
                mainViewInterface.movieDisplay(movie_response);

            }

            @Override
            public void onError( @NonNull Throwable e) {
                Log.d(TAG,"Error" + e);
                e.printStackTrace();
                //mainViewInterface.movieDisplay();

            }

            @Override
            public void onComplete() {

                Log.d(TAG,"Done");

            }
        };
    }
}
