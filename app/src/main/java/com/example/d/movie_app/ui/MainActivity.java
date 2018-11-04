package com.example.d.movie_app.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.d.movie_app.R;
import com.example.d.movie_app.adapter.Movie_Adapter;
import com.example.d.movie_app.data_models.Movie_Response;
import com.example.d.movie_app.networking.NetworkingInterface;
import com.example.d.movie_app.ui.main.MainPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainViewInterface  {

    @BindView(R.id.recyclerview_view)
    RecyclerView movieRv;

    private String TAG = "MainActivity";

    private RecyclerView.Adapter adapter;

    MainPresenter mainPresenter;

    //NetworkingInterface networkingInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        MVPsetup();
        viewSetup();
        getMovieList();
    }




    private void MVPsetup(){
        mainPresenter = new MainPresenter(this);
    }

    private void viewSetup(){
        movieRv.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getMovieList(){
        mainPresenter.getMovies();
    }


    @Override
    public void movieToast(String string) {
        Toast.makeText(MainActivity.this, string,Toast.LENGTH_LONG).show();
    }

    @Override
    public void movieDisplay(Movie_Response movie_response) {
        if (movie_response != null){
            Log.d(TAG, movie_response.getResults().get(1).getTitle());
            adapter = new Movie_Adapter(movie_response.getResults(), MainActivity.this);
            movieRv.setAdapter(adapter);
        } else {
            Log.d(TAG, " Movies are Null");
        }

    }

    @Override
    public void movieError(String string) {

        movieToast(string);
    }
}
