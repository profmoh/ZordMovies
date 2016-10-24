package com.movieapp.datazord.zordmovies.Movies;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.movieapp.datazord.zordmovies.BuildConfig;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by MFawzy on 10/24/2016.
 */

public class FetchMoviesTask extends AsyncTask<String, Void, List<Movie>> {
    private final String LOG_TAG = FetchMoviesTask.class.getSimpleName();

    public interface FetchMoviesTaskCallBacks {
        public void onPostExecute(List<Movie> movies);
    }

    private FetchMoviesTaskCallBacks fetchMoviesTaskCallBacks;

    public void setFetchMoviesTaskCallBacks(FetchMoviesTaskCallBacks fetchMoviesTaskCallBacks) {
        this.fetchMoviesTaskCallBacks = fetchMoviesTaskCallBacks;
    }

    @Override
    protected void onPostExecute(List<Movie> movies) {
        if(fetchMoviesTaskCallBacks == null)
            return;

        fetchMoviesTaskCallBacks.onPostExecute(movies);
    }

    @Override
    protected List<Movie> doInBackground(String... params) {
        if (params.length == 0)
            return null;

        String moviesJsonStr = null;
        BufferedReader reader = null;
        HttpURLConnection urlConnection = null;

        try {
            String movieType = params[0];
            final String API_KEY_PARAM = "api_key";
            final String FORECAST_BASE_URL = "http://api.themoviedb.org/3/movie/" + movieType + "?";

            Uri builtUri = Uri.parse(FORECAST_BASE_URL).buildUpon()
                    .appendQueryParameter(API_KEY_PARAM, BuildConfig.MOVIES_API_KEY)
                    .build();

            URL url = new URL(builtUri.toString());

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            StringBuffer buffer = new StringBuffer();
            InputStream inputStream = urlConnection.getInputStream();

            if (inputStream == null)
                return null;

            String line;
            reader = new BufferedReader(new InputStreamReader(inputStream));

            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0)
                return null;

            moviesJsonStr = buffer.toString();
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error ", e);
            return null;
        } finally {
            if (urlConnection != null)
                urlConnection.disconnect();

            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }

        try {
            return getMoviesDataFromJson(moviesJsonStr);
        } catch (JSONException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();
        }

        return null;
    }

    private List<Movie> getMoviesDataFromJson(String moviesJsonStr) throws JSONException {
        Gson gson = new GsonBuilder().create();
        MoviesJSONResult moviesJSONResult = gson.fromJson(moviesJsonStr, MoviesJSONResult.class);

        return moviesJSONResult.getMovies();
    }
}