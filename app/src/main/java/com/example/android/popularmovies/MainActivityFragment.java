package com.example.android.popularmovies;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.example.android.popularmovies.utils.Movie;
import com.example.android.popularmovies.utils.MovieJson;
import com.example.android.popularmovies.utils.NetworkUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivityFragment extends Fragment {

    private static final String TAG = MainActivityFragment.class.getSimpleName();
    private static MovieAdapter movieAdapter;

    private static List<Movie> androidFlavors  = new ArrayList<Movie>();

    public MainActivityFragment() {
        loadMoviesData(MainActivity.getFilter());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.content_main, container, false);

        movieAdapter = new MovieAdapter(getActivity(), androidFlavors);

        GridView gridView = (GridView) rootView.findViewById(R.id.movie_grid);
        gridView.setAdapter(movieAdapter);

        return rootView;
    }

    public static void loadMoviesData(String filter) {
        //showMoviesDataView();
        new FetchMoviesTask().execute(filter);
    }

    public static class FetchMoviesTask extends AsyncTask<String, Void, List<Movie>> {
        private final String TAG = FetchMoviesTask.class.getSimpleName();

        protected void onPostExecute(List<Movie> movieData) {
            //mLoadingIndicator.setVisibility(View.INVISIBLE);
            Log.v(TAG,"onPostExecute: " +  movieData);
            if (movieData != null)
                movieAdapter.setMovieData(movieData);
        }

        protected void onPreExecute() {
            super.onPreExecute();
            //mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        protected List<Movie> doInBackground(String... params) {
            if(params.length == 0){
                return null;
            }
            Log.v(TAG,"Filter usado: " + params[0]);
            String filter = params[0];
            URL movieRequestUrl = NetworkUtils.buildUrl(filter);
            Log.v(TAG,"Movie request: " + movieRequestUrl.toString());
            try {
                androidFlavors.clear();
                String jsonMovieResponse = NetworkUtils.getResponseFromHttpURL(movieRequestUrl);
                Log.v(TAG,"Response Json: " + jsonMovieResponse);
                List<Movie>  jsonAllMovies = MovieJson.getMovieFromJson(jsonMovieResponse);
                Log.v(TAG,"jsonAllMovies: " + jsonAllMovies.get(0).getPath_image());
                androidFlavors.addAll(jsonAllMovies);
                return jsonAllMovies;
            } catch (Exception e){
                e.printStackTrace();
                return null;
            }

        }
    }
}