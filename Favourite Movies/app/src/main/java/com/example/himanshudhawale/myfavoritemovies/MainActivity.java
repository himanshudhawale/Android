package com.example.himanshudhawale.myfavoritemovies;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Movie;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button addMovieButton, editMovieButton, deleteMovieButton, showListByYearButton, showListByRatingButton;
    private int REQ_MOVIE_INFO=100, j=0;
    private int REQ_EDIT=50;
    public ArrayList<Movies> listOfMovies=new ArrayList<>();
    private String arr[];
    static String EDIT_KEY="edit";
    static String ADD_KEY="add";
    static String BYYEAR="year";
    static String BYRATING="rating";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        Movies m1= new Movies("Naruto", "Japanese movie", "Animation",5,2008,"http://www.imdb.com/naruto" );
        Movies m2= new Movies("Naruto 2", "Japanese movie", "Comedy",4,2010,"http://www.imdb.com/narutotwo" );
        Movies m3= new Movies("Raees", "Laila song", "Action",3,2017,"http://www.imdb.com/raees" );
        Movies m4= new Movies("Death Note", "Based on anime", "Animation",5,2016,"http://www.imdb.com/deathnote" );

        listOfMovies.add(m1);
        listOfMovies.add(m2);
        listOfMovies.add(m3);
        listOfMovies.add(m4); */

        addMovieButton=findViewById(R.id.button1);
        editMovieButton=findViewById(R.id.button2);
        deleteMovieButton=findViewById(R.id.button3);
        showListByYearButton=findViewById(R.id.button4);
        showListByRatingButton=findViewById(R.id.button5);


        addMovieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intenttoAddMovie= new Intent(MainActivity.this, AddMovieActivity.class);
                startActivityForResult(intenttoAddMovie, REQ_MOVIE_INFO);

            }
        });


        editMovieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listOfMovies.size() == 0) {
                    Toast.makeText(MainActivity.this, "Add movies to edit", Toast.LENGTH_SHORT).show();
                } else {

                    final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Pick a Movie");

                    arr = new String[listOfMovies.size()];

                    for (int i = 0; i < listOfMovies.size(); i++)

                    {
                        arr[i] = listOfMovies.get(i).movieName;
                    }


                    builder.setItems(arr, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                            for (j = 0; j < listOfMovies.size(); j++) {
                                if (arr[which].equals(listOfMovies.get(j).movieName)) {
                                    break;
                                }
                            }


                            Intent intentToEditMovie = new Intent(MainActivity.this, EditMovieActivity.class);

                            intentToEditMovie.putExtra(EDIT_KEY, listOfMovies.get(j));
                            startActivityForResult(intentToEditMovie, REQ_EDIT);

                        }
                    });
                    final AlertDialog alert = builder.create();
                    alert.show();


                }
            }
        });


        deleteMovieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listOfMovies.size() == 0) {
                    Toast.makeText(MainActivity.this, "No movies to delete", Toast.LENGTH_SHORT).show();
                } else {

                    final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Pick a Movie");

                    arr = new String[listOfMovies.size()];

                    for (int i = 0; i < listOfMovies.size(); i++)

                    {
                        arr[i] = listOfMovies.get(i).movieName;
                    }


                    builder.setItems(arr, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                            for (j = 0; j < listOfMovies.size(); j++) {
                                if (arr[which].equals(listOfMovies.get(j).movieName)) {
                                    break;
                                }
                            }

                            Toast.makeText(MainActivity.this, "You've deleted " + listOfMovies.get(j).movieName, Toast.LENGTH_SHORT).show();

                            listOfMovies.remove(listOfMovies.get(j));


                        }
                    });
                    final AlertDialog alert = builder.create();
                    alert.show();


                }
            }
        });



        showListByYearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 if(listOfMovies.size()==0)
                 {
                     Toast.makeText(MainActivity.this, "No movies added to view this", Toast.LENGTH_SHORT).show();
                 }
                 else {

                     Intent intentYear = new Intent("com.example.himanshudhawale.myfavoritemovies.intent.action.SEND");
                     intentYear.addCategory(Intent.CATEGORY_DEFAULT);

                     intentYear.putParcelableArrayListExtra(BYYEAR, listOfMovies);
                     startActivity(intentYear);



                 }

            }
        });


        showListByRatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listOfMovies.size()==0)
                {
                    Toast.makeText(MainActivity.this, "No movies added to view this", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intentRating = new Intent("com.example.himanshudhawale.myfavoritemovies.intent.action.SEND1");
                    intentRating.addCategory(Intent.CATEGORY_DEFAULT);
                    intentRating.putParcelableArrayListExtra(BYRATING, listOfMovies);
                    startActivity(intentRating);
                }
            }
        });










    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
       if(requestCode==REQ_MOVIE_INFO)
       {

           if(resultCode == RESULT_OK)
           {

               if (data != null && data.getExtras() != null)
               {


                   Movies movies= data.getExtras().getParcelable(ADD_KEY);
                    listOfMovies.add(movies);


               }

           }
           else if(requestCode==RESULT_CANCELED)
           {
               Log.d("demo","Result canceled");

           }
       }
       if(requestCode==REQ_EDIT)
       {
           if(resultCode==RESULT_OK)
           {
               Movies movies= data.getExtras().getParcelable(EDIT_KEY);
               listOfMovies.set(j, movies);

           }
           else if(requestCode==RESULT_CANCELED)
           {
                Log.d("demo","Result canceled");
           }
       }

    }
}
