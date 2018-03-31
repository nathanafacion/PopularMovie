package com.example.android.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmovies.utils.Movie;
import com.squareup.picasso.Picasso;

public class DetailMovieActivity extends AppCompatActivity {

    private static final String TAG = DetailMovieActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        Intent intentDetailItem = getIntent();

        TextView  title = findViewById(R.id.tv_title_detail);
        TextView overview = findViewById(R.id.tv_overview_detail);
        TextView release = findViewById(R.id.tv_release_detail);
        TextView average = findViewById(R.id.tv_average_detail);
        ImageView poster = findViewById(R.id.iv_poster_detail);

        if (intentDetailItem != null) {
            Movie movie = intentDetailItem.getExtras().getParcelable("movie");

            title.setText(movie.getOriginal_title());
            overview.setText(movie.getOverview());
            release.setText(getString(R.string.release)+ ": " + AlterDate(movie.getRelease_date()));
            average.setText(getString(R.string.average)+ ": " + String.valueOf(movie.getVote_average()));
            Picasso.with(this).load("http://image.tmdb.org/t/p/w780/" + movie.getBackdrop_path()).into(poster);

            Log.v(TAG, " Detail id: " + movie.getId());
            Log.v(TAG, " Detail title: " + movie.getOriginal_title());
            Log.v(TAG, " Detail overview: " + movie.getOverview());
            Log.v(TAG, " Detail release: " + movie.getRelease_date());
            Log.v(TAG, " Detail average: " + movie.getVote_average());
        }

    }

    public String AlterDate( String date){
        String newRelease[] = new String[3];
        newRelease = date.split("-");
        return newRelease[2]+"/"+newRelease[1] + "/" + newRelease[0];
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

}
