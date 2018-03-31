package com.example.android.popularmovies.utils;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;

public class MovieJson {

    private static final String TAG = MovieJson.class.getSimpleName();

    public static ArrayList<Movie> getMovieFromJson(String JsonStr)
            throws JSONException {
        final String id = "id";
        final String path_image = "poster_path";
        final String name_results = "results";
        final String overview = "overview";
        final String vote_average = "vote_average";
        final String release = "release_date";
        final String original_title = "original_title";
        final String backdrop_path = "backdrop_path";

        JSONObject moviesJson = new JSONObject(JsonStr);
        boolean validation = validationJson(moviesJson);
        Log.v(TAG, "Boolean json : " + validation);
        if (!validation) return null;

        JSONArray resultsMovie = moviesJson.getJSONArray(name_results);

        ArrayList<Movie> allMovies = new ArrayList<Movie>();

        int movieId;
        String moviePath_image , movieRelease, movieOverview, movieOriginal_title, movieBackdrop_path;
        Double movieVote_average;
        JSONObject movieJson;
        for(int i =0; i< resultsMovie.length(); i++){
            movieJson = resultsMovie.getJSONObject(i);
            movieRelease = movieJson.getString(release);
            Log.v(TAG, "MovieJson release: " + movieRelease);
            movieOriginal_title = movieJson.getString(original_title);
            Log.v(TAG, "MovieJson original_title: " + movieOriginal_title);
            movieOverview= movieJson.getString(overview);
            Log.v(TAG, "MovieJson overview: " + movieOverview);
            movieVote_average = movieJson.getDouble(vote_average);
            Log.v(TAG, "MovieJson vote_average: " + movieVote_average);
            movieId = movieJson.getInt(id);
            Log.v(TAG, "MovieJson id: " + movieId);
            moviePath_image = movieJson.getString(path_image);
            Log.v(TAG, "MovieJson path: " + moviePath_image);
            movieBackdrop_path = movieJson.getString(backdrop_path);
            Movie itemMovie = new Movie(movieId, moviePath_image, movieOriginal_title, movieOverview, movieVote_average, movieRelease,movieBackdrop_path);
            allMovies.add(itemMovie);
        }

        return allMovies;
    }

    public static boolean validationJson(JSONObject moviesJson) throws JSONException{
        final String OWM_MESSAGE_CODE = "page";

        int errorCode = moviesJson.getInt(OWM_MESSAGE_CODE);
        Log.v(TAG,"ErrorCode : " + errorCode);
        if(moviesJson.has(OWM_MESSAGE_CODE)){
            return true;
        }
        return false;
        //final String OWM_MESSAGE_CODE = "cod";
        //if (moviesJson.has(OWM_MESSAGE_CODE)) {
            //int errorCode = moviesJson.getInt(OWM_MESSAGE_CODE);
            //switch (errorCode) {
             //   case HttpURLConnection.HTTP_OK:
             //       return true;
             //   case HttpURLConnection.HTTP_NOT_FOUND:
                    /* Location invalid */
             //       return false;
             //   default:
                    /* Server probably down */
             //       return false;
            //}

        //}
        //return false;
    }
}
