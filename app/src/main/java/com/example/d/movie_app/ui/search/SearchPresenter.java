package com.example.d.movie_app.ui.search;

import android.support.annotation.NonNull;
import android.support.v7.widget.SearchView;
import android.util.Log;

import com.example.d.movie_app.data_models.Movie_Response;
import com.example.d.movie_app.networking.NetworkClient;
import com.example.d.movie_app.networking.NetworkingInterface;

import java.util.concurrent.TimeUnit;
import io.reactivex.functions.Predicate;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class SearchPresenter implements SearchPresenterInterface {

    private String TAG = "Search Presenter";

    SearchViewInterface searchViewInterface;

    public SearchPresenter(SearchViewInterface searchViewInterface) {
        this.searchViewInterface = searchViewInterface;
    }

    @Override
    public void getResultsBasedOnQuery(SearchView searchView) {

        getObservableQuery(searchView)
                .filter(new Predicate<String>() {

                    @Override
                    public boolean test(@NonNull String s) throws Exception {
                        if (s.equals("")){
                            return false;
                        } else {
                            return true;
                        }
                    }
                })

                    .debounce(2, TimeUnit.SECONDS)
                    .distinctUntilChanged()
                    .switchMap(new Function<String, io.reactivex.Observable<Movie_Response>>() {
                        @Override
                        public io.reactivex.Observable<Movie_Response> apply(String s) throws Exception {
                            return NetworkClient.getRetrofit().create(NetworkingInterface.class)
                                    .getMovies(NetworkingInterface.API_KEY,s);
                        }
                    })

                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObserver());

    }


    private Observable<String> getObservableQuery(SearchView searchView){

        final PublishSubject<String> publishSubject = PublishSubject.create();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                publishSubject.onNext(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                publishSubject.onNext(newText);
                return true;
            }
        });

        return publishSubject;
    }


    public DisposableObserver<Movie_Response> getObserver(){
        return new DisposableObserver<Movie_Response>() {
            @Override
            public void onNext(@NonNull Movie_Response movie_response) {
                Log.d(TAG,"OnNext" + movie_response.getTotalResults());
                searchViewInterface.movieDisplay(movie_response);

            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e);
                e.printStackTrace();
                searchViewInterface.movieError("ERROR GETTING MOVIE DATA");

            }

            @Override
            public void onComplete() {
                Log.d(TAG, "COMPLETE");
               // searchViewInterface.movieToast("WE HAVE MOVIES!!");

            }
        };
    }

}
