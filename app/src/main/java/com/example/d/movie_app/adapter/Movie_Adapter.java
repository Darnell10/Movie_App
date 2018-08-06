package com.example.d.movie_app.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.d.movie_app.R;
import com.example.d.movie_app.data_models.Result;
import com.example.d.movie_app.ui.MainActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Movie_Adapter extends RecyclerView.Adapter<Movie_Adapter.Movie_Holder> {

    List<Result> movieList;
    Context context;

    public Movie_Adapter(List<Result> results, MainActivity mainActivity) {
    }


    @NonNull
    @Override
    public Movie_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_layout,parent,false);
        return new Movie_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Movie_Holder holder, int position) {

        holder.movieTitle.setText(movieList.get(position).getTitle());
        holder.overView.setText(movieList.get(position).getOverview());
        holder.releaseDate.setText(movieList.get(position).getReleaseDate());
        Result movieResult = movieList.get(position);
        holder.onBind(movieResult);

//                .load("https://image.themoviedb.org/3/movie/550"+movieList
//                        .get(position).getPosterPath()).into(holder.movieImage);

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class Movie_Holder extends RecyclerView.ViewHolder {

        private ImageView movieImage;
        private TextView movieTitle;
        private TextView releaseDate;
        private TextView overView;


        public Movie_Holder(View itemView) {
            super(itemView);

            movieImage = itemView.findViewById(R.id.movie_imageview);
            movieTitle = itemView.findViewById(R.id.movie_title);
            releaseDate = itemView.findViewById(R.id.release_date);
            overView = itemView.findViewById(R.id.overview);
            movieImage = itemView.findViewById(R.id.movie_imageview);
        }

        public void onBind(Result movieResult) {
            Picasso.with(itemView.getContext())
                    .load(movieResult.getPosterPath())
                    .into(movieImage);
        }
    }

}
