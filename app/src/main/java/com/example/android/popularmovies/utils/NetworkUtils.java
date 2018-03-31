package com.example.android.popularmovies.utils;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {
    private static final String TAG = NetworkUtils.class.getSimpleName();
    private static final String UrlBase = "https://api.themoviedb.org/3/movie/";
    //sort_by
    private static final String sortBy = "sort_by";
    //api_key
    private static final String api_key = "api_key";
    private static final String key = "";
    private static final String language = "language";
    private static final String language_br = "pt-BR";

    public static URL buildUrl(String filter){
        Uri buildUri = Uri.parse(UrlBase + filter).buildUpon()
        //        .appendQueryParameter(sortBy,filter)
                .appendQueryParameter(api_key,key)
        //        .appendQueryParameter(language,language_br)
                .build();

        URL url = null;
        try {
            url = new URL(buildUri.toString());
        } catch(MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG,"Built URI: " + url);

        return url;
    }

    public static String getResponseFromHttpURL( URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        Log.v(TAG,"Connection: " + urlConnection.getResponseCode());
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            Log.v(TAG,"Has Input: " + hasInput);
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

}
