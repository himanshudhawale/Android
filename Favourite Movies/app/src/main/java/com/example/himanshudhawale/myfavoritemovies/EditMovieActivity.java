package com.example.himanshudhawale.myfavoritemovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class EditMovieActivity extends AppCompatActivity {


    private TextView percentage;
    private EditText movieNameEditText,movieDescriptionEditText,movieYearEditText,imdbEditText;
    private Spinner movieGenreDropDown;
    private SeekBar movieRatingSeekBar;
    private Button saveChanges;
    private String genreSelected,movieName,movieDesc,movieImdb;
    private int seekBarProgress;
    private Integer movieYear;
    static String EDIT_RETURN_KEY="editReturn";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie);

        movieNameEditText=findViewById(R.id.movieNameEditTextID);
        movieDescriptionEditText=findViewById(R.id.movieDescriptionEditTextID);
        movieYearEditText=findViewById(R.id.movieYearEditTextID);
        imdbEditText=findViewById(R.id.imdbEditTextID);
        movieGenreDropDown=findViewById(R.id.genreDropDownID);
        movieRatingSeekBar=findViewById(R.id.movieRatingSeekBarID);
        saveChanges=findViewById(R.id.saveChangesId);
        percentage=findViewById(R.id.textViewPercentId);






        if(getIntent() != null && getIntent().getExtras()!=null)
        {
            Movies movie = getIntent().getExtras().getParcelable(MainActivity.EDIT_KEY);

            movieNameEditText.setText(movie.movieName);
            movieDescriptionEditText.setText(movie.movieDesc);



            movieRatingSeekBar.setProgress(movie.movieRating);
            percentage.setText(String.valueOf(movie.movieRating));
            movieYearEditText.setText(String.valueOf(movie.movieYear));
            imdbEditText.setText(movie.movieImdb);



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

            switch (movie.movieGenre)
            {
                case "Select": movieGenreDropDown.setSelection(0);break;
                case "Action": movieGenreDropDown.setSelection(1); break;
                case "Animation": movieGenreDropDown.setSelection(2); break;
                case "Comedy": movieGenreDropDown.setSelection(3); break;
                case "Documentary": movieGenreDropDown.setSelection(4); break;
                case "Family": movieGenreDropDown.setSelection(5); break;
                case "Horror": movieGenreDropDown.setSelection(6); break;
                case "Crime": movieGenreDropDown.setSelection(7); break;
                case "Others": movieGenreDropDown.setSelection(8); break;


            }


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




            saveChanges.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(movieNameEditText.getText().toString().isEmpty())
                    {
                        Toast.makeText(EditMovieActivity.this, "Enter a movie name", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {

                        if(movieDescriptionEditText.getText().toString().trim().length()==0)
                        {
                            Toast.makeText(EditMovieActivity.this, "Enter description for the movie", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            if (genreSelected.equals("Select"))
                            {
                                Toast.makeText(EditMovieActivity.this, "You didn't select any genre", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                if(movieYearEditText.getText().toString().isEmpty() || movieYearEditText.getText().toString().length() < 4)
                                {
                                    Toast.makeText(EditMovieActivity.this, "Enter a valid year", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    if (imdbEditText.getText().toString().isEmpty())
                                    {
                                        Toast.makeText(EditMovieActivity.this, "Enter a IMDB link", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        if (!((imdbEditText.getText().toString().toLowerCase().startsWith("https://www.imdb.com/")) || (imdbEditText.getText().toString().toLowerCase().startsWith("http://www.imdb.com/"))))
                                        {
                                            Toast.makeText(EditMovieActivity.this, "Enter a valid IMDB link", Toast.LENGTH_SHORT).show();
                                        }
                                        else{
                                            if (imdbEditText.getText().toString().length() > 0 && imdbEditText.getText().toString().contains(" ")) {
                                                Toast.makeText(EditMovieActivity.this, "Space not allowed in IMDB link", Toast.LENGTH_SHORT).show();
                                            }
                                            else {
                                            movieName=movieNameEditText.getText().toString();
                                            movieDesc=movieDescriptionEditText.getText().toString();
                                            movieYear=Integer.parseInt(movieYearEditText.getText().toString());
                                            movieImdb=imdbEditText.getText().toString();
                                            seekBarProgress= Integer.parseInt(percentage.getText().toString());



                                            Intent editIntent=new Intent();
                                            editIntent.putExtra(MainActivity.EDIT_KEY,new Movies(movieName,movieDesc,genreSelected,seekBarProgress,movieYear,movieImdb));
                                            setResult(RESULT_OK,editIntent);

                                    }
                                    finish();
                                    Toast.makeText(EditMovieActivity.this, "Changes has been saved", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                    }
                }}
            });


        }






    }
}
