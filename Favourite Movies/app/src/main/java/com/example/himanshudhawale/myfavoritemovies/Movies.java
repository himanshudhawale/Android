package com.example.himanshudhawale.myfavoritemovies;


import android.os.Parcel;
import android.os.Parcelable;

public class Movies implements Parcelable {

    String movieName;
    String movieDesc;
    String movieGenre;
    int movieRating;
    int movieYear;
    String movieImdb;


    protected Movies(Parcel in) {
        this.movieName = in.readString();
        this.movieDesc = in.readString();
        this.movieGenre = in.readString();
        this.movieRating = in.readInt();
        this.movieYear = in.readInt();
        this.movieImdb = in.readString();
    }

    public static final Creator<Movies> CREATOR = new Creator<Movies>() {
        @Override
        public Movies createFromParcel(Parcel in) {
            return new Movies(in);
        }

        @Override
        public Movies[] newArray(int size) {
            return new Movies[size];
        }
    };

    public Movies(String movieName, String movieDesc, String movieGenre, int movieRating, int movieYear, String movieImdb) {
        this.movieName = movieName;
        this.movieDesc = movieDesc;
        this.movieGenre = movieGenre;
        this.movieRating = movieRating;
        this.movieYear = movieYear;
        this.movieImdb = movieImdb;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.movieName);
        dest.writeString(this.movieDesc);
        dest.writeString(this.movieGenre);
        dest.writeInt(this.movieRating);
        dest.writeInt(this.movieYear);
        dest.writeString(this.movieImdb);
    }

    @Override
    public String toString() {
        return "Movies{" +
                "movieName='" + movieName + '\'' +
                ", movieDesc='" + movieDesc + '\'' +
                ", movieGenre='" + movieGenre + '\'' +
                ", movieRating=" + movieRating +
                ", movieYear=" + movieYear +
                ", movieImdb='" + movieImdb + '\'' +
                '}';
    }


}