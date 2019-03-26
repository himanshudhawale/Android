package com.example.himanshudhawale.areacalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView imageViewTri, imageViewCir,imageViewSqu;
    private Button calculate,clear;
    private TextView textViewShape, textViewResult,textViewlength;
    private EditText getLength1, getLength2;
    private double l1, l2,area=0.0;
    private int i=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imageViewTri=findViewById(R.id.imageViewTriangle);
        imageViewSqu=findViewById(R.id.imageViewSquare);
        imageViewCir=findViewById(R.id.imageViewCircle);
        getLength1=findViewById(R.id.editTextLength1);
        getLength2=findViewById(R.id.editTextLength2);
        textViewShape=findViewById(R.id.textViewShape);
        textViewlength=findViewById(R.id.textViewLength2);
        textViewResult=findViewById(R.id.result);

        calculate=findViewById(R.id.calculateButton);
        clear=findViewById(R.id.clearButton);






        imageViewTri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getLength2.setVisibility((getLength2.getVisibility() == View.VISIBLE) ? View.VISIBLE : View.VISIBLE);
                textViewlength.setVisibility((textViewlength.getVisibility() == View.VISIBLE) ? View.VISIBLE : View.VISIBLE);

                textViewShape.setText("Triangle");
                i=2;



            }
        });


        imageViewSqu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getLength2.setVisibility((getLength2.getVisibility() == View.VISIBLE) ? View.GONE : View.GONE);
                textViewlength.setVisibility((textViewlength.getVisibility() == View.VISIBLE) ? View.GONE : View.GONE);
                getLength2.setText("");
                textViewShape.setText("Square");
                i=3;

            }
        });

        imageViewCir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getLength2.setVisibility((getLength2.getVisibility() == View.VISIBLE)
                        ? View.GONE : View.GONE);
                textViewlength.setVisibility((textViewlength.getVisibility() == View.VISIBLE)
                        ? View.GONE : View.GONE);
                getLength2.setText("");

                textViewShape.setText("Circle");
                i=4;

            }
        });


        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if(i==0)
                {
                    Toast.makeText(MainActivity.this, "Please select a shape", Toast.LENGTH_LONG).show();
                    textViewResult.setText("");
                }

                else {

                    if (getLength1.getText().toString().isEmpty()) {
                        Toast.makeText(MainActivity.this, "Please enter lengths", Toast.LENGTH_LONG).show();

                    } else {

                        l1 = Double.parseDouble(getLength1.getText().toString());

                        if (i == 2) {
                            if (getLength2.getText().toString().isEmpty()) {
                                Toast.makeText(MainActivity.this, "Please enter lengths", Toast.LENGTH_LONG).show();                            } else {
                                l2 = Double.parseDouble(getLength2.getText().toString());
                            }


                            area = 0.5 * l1 * l2;
                        } else if (i == 3) {

                            area = l1 * l1;
                        } else if (i == 4) {
                            area = 3.1416 * l1 * l1;
                        }

                        if (getLength2.getText().toString().isEmpty() && i!=3 && i!=4) {
                            textViewResult.setText("");
                        } else {

                            textViewResult.setText(String.valueOf(area));
                        }
                    }
                }


            }

        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLength1.setText("");

                getLength2.setText("");

                textViewShape.setText("Select a shape");
                area=0.0;
                textViewResult.setText("");
                getLength2.setVisibility(View.VISIBLE);
                textViewlength.setVisibility(View.VISIBLE);
                i=0;

            }
        });


    }
}
