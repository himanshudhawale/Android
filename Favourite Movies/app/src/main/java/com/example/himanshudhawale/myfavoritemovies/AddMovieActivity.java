package com.example.himanshudhawale.myfavoritemovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddMovieActivity extends AppCompatActivity {

    private TextView percentage;
    private EditText movieNameEditText,movieDescriptionEditText,movieYearEditText,imdbEditText;
    private Spinner movieGenreDropDown;
    private SeekBar movieRatingSeekBar;
    private Button addMovieButton;
    private String genreSelected,movieName,movieDesc,movieImdb;
    private int seekBarProgress;
    private Integer movieYear;
    static String MOVIE_KEY="movie";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        movieNameEditText=findViewById(R.id.movieNameEditTextID);
        movieDescriptionEditText=findViewById(R.id.movieDescriptionEditTextID);
        movieYearEditText=findViewById(R.id.movieYearEditTextID);
        imdbEditText=findViewById(R.id.imdbEditTextID);
        movieGenreDropDown=findViewById(R.id.genreDropDownID);
        movieRatingSeekBar=findViewById(R.id.movieRatingSeekBarID);
        addMovieButton=findViewById(R.id.addMovieButtonID);
        percentage=findViewById(R.id.textViewPercentId);



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.movieGenreList, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        movieGenreDropDown.setAdapter(adapter);
        movieGenreDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


               genreSelected=parent.getItemAtPosition(position).toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Another interface callback
            }
        });

        movieRatingSeekBar.setProgress(0);
        //movieRatingSeekBar.incrementProgressBy(1);
        movieRatingSeekBar.setMax(5);






        movieRatingSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                seekBarProgress=progress;
                percentage.setText(String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });


        addMovieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (movieNameEditText.getText().toString().isEmpty()) {
                    Toast.makeText(AddMovieActivity.this, "Enter a movie name", Toast.LENGTH_SHORT).show();
                } else {

                    if (movieDescriptionEditText.getText().toString().trim().length() == 0) {
                        Toast.makeText(AddMovieActivity.this, "Enter description for the movie", Toast.LENGTH_SHORT).show();
                    } else {
                        if (genreSelected.equals("Select")) {
                            Toast.makeText(AddMovieActivity.this, "You didn't select any genre", Toast.LENGTH_SHORT).show();
                        } else {
                            if (movieYearEditText.getText().toString().isEmpty() || movieYearEditText.getText().toString().length() < 4) {
                                Toast.makeText(AddMovieActivity.this, "Enter a valid year", Toast.LENGTH_SHORT).show();
                            } else {

                                if (imdbEditText.getText().toString().isEmpty()) {

                                    Toast.makeText(AddMovieActivity.this, "Enter a IMDB link", Toast.LENGTH_SHORT).show();
                                } else {
                                    if (!((imdbEditText.getText().toString().toLowerCase().startsWith("https://www.imdb.com/")) || (imdbEditText.getText().toString().toLowerCase().startsWith("http://www.imdb.com/")))) {
                                        Toast.makeText(AddMovieActivity.this, "Enter a valid IMDB link", Toast.LENGTH_SHORT).show();
                                    } else {
                                        if (imdbEditText.getText().toString().length() > 0 && imdbEditText.getText().toString().contains(" ")) {
                                            Toast.makeText(AddMovieActivity.this, "Space not allowed in IMDB link", Toast.LENGTH_SHORT).show();
                                        } else {
                                            movieName = movieNameEditText.getText().toString();
                                            movieDesc = movieDescriptionEditText.getText().toString();
                                            movieYear = Integer.parseInt(movieYearEditText.getText().toString());
                                            movieImdb = imdbEditText.getText().toString();


                                            Intent addIntent = new Intent();
                                            addIntent.putExtra(MainActivity.ADD_KEY, new Movies(movieName, movieDesc, genreSelected, seekBarProgress, movieYear, movieImdb));

                                            setResult(RESULT_OK, addIntent);
                                            finish();
                                            Toast.makeText(AddMovieActivity.this, movieName + " has been added", Toast.LENGTH_SHORT).show();


                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });


    }
}
