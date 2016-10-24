package com.movieapp.datazord.zordmovies.Movies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.movieapp.datazord.zordmovies.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MFawzy on 10/24/2016.
 */

public class MoviesAdapter extends BaseAdapter {
    Context mContext;
    List<Movie> movies;

    private final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/";

    public MoviesAdapter() {
        movies = new ArrayList<Movie>();
    }

    public MoviesAdapter(Context mContext) {
        this.mContext = mContext;
        movies = new ArrayList<Movie>();
    }

    public MoviesAdapter(List<Movie> movies, Context mContext) {
        this.movies = movies;
        this.mContext = mContext;
    }

    public void add(Movie movie) {
        this.movies.add(movie);
    }

    public void addAll(List<Movie> movies) {
        this.movies.addAll(movies);
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return movies.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        View view = convertView;

        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.fragment_movies, null);

            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) view.getTag();

        viewHolder.movieName.setText(movies.get(0).getTitle());
        Picasso.with(mContext).load(IMAGE_BASE_URL + movies.get(0).getPosterPath()).into(viewHolder.imageView);

        return view;
    }

    public class ViewHolder {
        TextView movieName;
        ImageView imageView;

        public ViewHolder(View view) {
            movieName = (TextView) view.findViewById(R.id.movie_name);
            imageView = (ImageView) view.findViewById(R.id.movie_image);
        }
    }
}
