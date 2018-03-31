package com.example.android.popularmovies.utils;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie  implements Parcelable{
    private final int id ;
    private final String path_image;
    private final String original_title;
    private final String overview;
    private final Double vote_average;
    private final String release_date;
    private final String backdrop_path;


    public Movie(int id, String path_image, String original_title, String overview, Double vote_average, String release_date, String backdrop_path) {
        this.id = id;
        this.path_image = path_image;
        this.original_title = original_title;
        this.overview = overview;
        this.vote_average = vote_average;
        this.release_date = release_date;
        this.backdrop_path = backdrop_path;
    }

    protected Movie(Parcel in) {
        id = in.readInt();
        vote_average = in.readDouble();
        String[] data = in.createStringArray();
        this.original_title = data[0];
        this.overview = data[1];
        this.release_date = data[2];
        this.path_image = data[3];
        this.backdrop_path = data[4];

    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getOverview() {
        return overview;
    }

    public Double getVote_average() {
        return vote_average;
    }

    public String getRelease_date() {
        return release_date;
    }

    public int getId() {
        return id;
    }

    public String getPath_image() {
        return path_image;
    }


    public String getBackdrop_path() {
        return backdrop_path;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeDouble(vote_average);
        dest.writeStringArray(new String[] {
                this.original_title,
                this.overview,
                this.release_date,
                this.path_image,
                this.backdrop_path
        });
    }
    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
