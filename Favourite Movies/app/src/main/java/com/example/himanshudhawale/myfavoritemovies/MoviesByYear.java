package com.example.himanshudhawale.myfavoritemovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class MoviesByYear extends AppCompatActivity {

    private TextView tvTitle, tvDescription, tvGenre, tvRating, tvYear, tvImdb;
    private Button finishbutton;
    private ImageView ivPrev, ivNext, ivLast, ivFirst;
    ArrayList<Movies> list= new ArrayList<>();
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_by_year);

        tvTitle=findViewById(R.id.tvTitleId);
        tvDescription=findViewById(R.id.tvDescriptionId);
        tvGenre=findViewById(R.id.tvGenreId);
        tvRating=findViewById(R.id.tvRatingId);
        tvYear=findViewById(R.id.tvYearId);
        tvImdb=findViewById(R.id.tvImdbId);
        finishbutton=findViewById(R.id.finishbuttonId);
        ivFirst=findViewById(R.id.ivFirstId);
        ivPrev=findViewById(R.id.ivprevId);
        ivNext=findViewById(R.id.ivNextId);
        ivLast=findViewById(R.id.ivLastId);


        if (getIntent() !=null && getIntent().getExtras() !=null)
        {
            String action= getIntent().getAction();
            String type=getIntent().getType();


            list=getIntent().getExtras().getParcelableArrayList(MainActivity.BYYEAR);
           // Log.d("demo",list.toString());
            final int j=list.size();

            Collections.sort(list, new Comparator<Movies>() {
                public int compare(Movies o1, Movies o2){
                    return Integer.compare(o1.movieYear, o2.movieYear);
                }

            });
            i=0;
            tvTitle.setText(list.get(i).movieName);
            tvDescription.setText(list.get(i).movieDesc);
            tvGenre.setText(list.get(i).movieGenre);
            tvRating.setText(String.valueOf(list.get(i).movieRating));
            tvYear.setText(String.valueOf(list.get(i).movieYear));
            tvImdb.setText(list.get(i).movieImdb);


            ivFirst.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(i==0)
                    {
                        Toast.makeText(MoviesByYear.this, "This is the first movie", Toast.LENGTH_SHORT).show();
                    }

                    else {
                        i = 0;

                        tvTitle.setText(list.get(i).movieName);
                        tvDescription.setText(list.get(i).movieDesc);
                        tvGenre.setText(list.get(i).movieGenre);
                        tvRating.setText(String.valueOf(list.get(i).movieRating));
                        tvYear.setText(String.valueOf(list.get(i).movieYear));
                        tvImdb.setText(list.get(i).movieImdb);
                    }

                }
            });


            ivPrev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(i==0)
                    {
                        Toast.makeText(MoviesByYear.this, "This is the first movie", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        i--;

                        tvTitle.setText(list.get(i).movieName);
                        tvDescription.setText(list.get(i).movieDesc);
                        tvGenre.setText(list.get(i).movieGenre);
                        tvRating.setText(String.valueOf(list.get(i).movieRating));
                        tvYear.setText(String.valueOf(list.get(i).movieYear));
                        tvImdb.setText(list.get(i).movieImdb);

                    }

                }
            });



            ivNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(i==j-1)
                    {
                        Toast.makeText(MoviesByYear.this, "This is the last movie", Toast.LENGTH_SHORT).show();
                    }

                    else
                    {
                        i++;

                        if(i<j) {
                            tvTitle.setText(list.get(i).movieName);
                            tvDescription.setText(list.get(i).movieDesc);
                            tvGenre.setText(list.get(i).movieGenre);
                            tvRating.setText(String.valueOf(list.get(i).movieRating));
                            tvYear.setText(String.valueOf(list.get(i).movieYear));
                            tvImdb.setText(list.get(i).movieImdb);
                        }
                        else{
                            Toast.makeText(MoviesByYear.this, "This is the last movie", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            });

            ivLast.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(i==j-1)
                    {
                        Toast.makeText(MoviesByYear.this, "This is the last movie", Toast.LENGTH_SHORT).show();
                    }
                    else
                        {
                        i = j - 1;
                        tvTitle.setText(list.get(i).movieName);
                        tvDescription.setText(list.get(i).movieDesc);
                        tvGenre.setText(list.get(i).movieGenre);
                        tvRating.setText(String.valueOf(list.get(i).movieRating));
                        tvYear.setText(String.valueOf(list.get(i).movieYear));
                        tvImdb.setText(list.get(i).movieImdb);
                    }
                }
            });




            finishbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });



        }




    }
}
