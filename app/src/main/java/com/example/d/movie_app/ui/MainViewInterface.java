package com.example.d.movie_app.ui;

import com.example.d.movie_app.data_models.Movie_Response;

public interface MainViewInterface {

    void movieToast(String string);

    void movieDisplay(Movie_Response movie_response);

    void movieError(String string);
}
