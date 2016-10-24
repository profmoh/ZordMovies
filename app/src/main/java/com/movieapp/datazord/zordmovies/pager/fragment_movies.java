package com.movieapp.datazord.zordmovies.pager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.movieapp.datazord.zordmovies.Movies.FetchMoviesTask;
import com.movieapp.datazord.zordmovies.Movies.Movie;
import com.movieapp.datazord.zordmovies.Movies.MoviesAdapter;
import com.movieapp.datazord.zordmovies.R;

import java.util.List;

/**
 * Created by MFawzy on 10/21/2016.
 */
public class fragment_movies extends Fragment{

    public fragment_movies() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_movies, container, false);

        fetchMovies(rootView);

//        Picasso.with(getContext())
//                .load("https://www.simplifiedcoding.net/wp-content/uploads/2015/10/advertise.png")
//                .placeholder(R.drawable.icon)
//                .error(R.drawable.icon)
//                .into(((ImageView) rootView.findViewById(R.id.movie_image)));

        return rootView;
    }

    public void fetchMovies(final View rootView) {
        FetchMoviesTask fetchMoviesTask = new FetchMoviesTask();

        fetchMoviesTask.setFetchMoviesTaskCallBacks(new FetchMoviesTask.FetchMoviesTaskCallBacks() {

            @Override
            public void onPostExecute(List<Movie> movies) {
                MoviesAdapter moviesAdapter = new MoviesAdapter(getContext());
//
                moviesAdapter.addAll(movies);
//                Picasso.with(getContext())
//                .load("https://www.simplifiedcoding.net/wp-content/uploads/2015/10/advertise.png")
//                .placeholder(R.drawable.icon)
//                .error(R.drawable.icon)
//                .into(((ImageView) rootView.findViewById(R.id.movie_image)));
                moviesAdapter.getView(0,(ImageView) rootView.findViewById(R.id.movie_image),(RelativeLayout) rootView.findViewById(R.id.relative));
            }
        });

        fetchMoviesTask.execute("popular");
    }
}