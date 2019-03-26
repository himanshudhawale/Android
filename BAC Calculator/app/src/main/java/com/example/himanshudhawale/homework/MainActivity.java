package com.example.himanshudhawale.homework;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;









public class MainActivity extends AppCompatActivity {

    private EditText editText_weight;
    private Button saveButton, addDrink,resetButton;
    private Switch genderSwitch;
    private RadioGroup radioGroup;
    private int drinkSize=1;
    private SeekBar seekBar;
    private TextView alcoholValue,bacLevelResult,status;
    private ProgressBar progressBar;
    private double genderConstant,bacPercent,alcoholConsumed;
    private  String enteredWeight;


    private Double weightInput=0.0,latestBACValue,totalbac=0.0;
    DecimalFormat myFormatter = new DecimalFormat("##0.0000");


    private void yourStatus(double barPercent){

        if(bacPercent <= 0.08) {
            status.setText("You're safe.");
            status.setBackgroundColor(Color.parseColor("#008000"));

        }

        else if(bacPercent > 0.08 && bacPercent < 0.20) {
            status.setText("Be careful...");
            status.setBackgroundColor(Color.parseColor("#FFA500"));

        }

        else if(bacPercent >= 0.20) {
            status.setText("Over the Limit!");
            status.setBackgroundColor(Color.parseColor("#FF0000"));

            if(bacPercent >= 0.25)
            {
                Toast.makeText(MainActivity.this, "No more drinks for you.", Toast.LENGTH_LONG).show();
                saveButton.setEnabled(false);
                addDrink.setEnabled(false);


            }
        }
    }


    private double genderReturn(Switch genderSwitch){

        if (genderSwitch.isChecked())
            return 0.55;

        else
            return 0.68;


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ActionBar for icon launcher begins

        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.mipmap.ic_launcher); //Check
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        //ActionBar for icon launcher end

        //Switch code begins

        genderSwitch = findViewById(R.id.genderSwitchId); // initiate Switch
        genderSwitch.setTextOff("M");
        genderSwitch.setTextOn("F");

        //Switch code ends

        //Enter Weight
        editText_weight= findViewById(R.id.weightValueId);

        //view of status
        status=findViewById(R.id.yourStatusValueId);


        //Save Button
        saveButton=findViewById(R.id.saveButtonId);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //After Click

                //Verification
                if (totalbac == 0.0) {

                  //Storing variables after clicking Save Button
                     enteredWeight = editText_weight.getText().toString();


                    if (editText_weight.getText().toString().trim().length() <= 0) {
                        editText_weight.requestFocus();
                        editText_weight.setError("Enter the weight in lbs");
                    } else {
                        weightInput = Double.parseDouble(enteredWeight);
                        //String genderInput= genderSwitch.getText().toString();

                        //Checking and storing Gender

                        genderConstant=genderReturn(genderSwitch);

                    }
                }

                else
                    {
                        //When BAC is non zero and gender has been changed
                        String enteredWeight = editText_weight.getText().toString();



                        if (editText_weight.getText().toString().trim().length() <= 0) {
                            editText_weight.requestFocus();
                            editText_weight.setError("Enter the weight in lbs");
                        }


                        else if(weightInput==0.0){
                             Toast.makeText(MainActivity.this,"Please save your weight and gender", Toast.LENGTH_SHORT).show();
                            //toast.setMargin(50,50);
                            //toast.show();


                        }
                        else {
                            weightInput = Double.parseDouble(enteredWeight);
                            //String genderInput= genderSwitch.getText().toString();

                            //genderConstant
                            genderConstant=genderReturn(genderSwitch);

                        }





                        //capital A calculated
                        weightInput=Double.parseDouble(editText_weight.getText().toString());


                        latestBACValue=((alcoholConsumed)*6.24)/(weightInput*genderConstant);


                        totalbac=latestBACValue;

                        bacPercent=totalbac/100;


                        bacLevelResult.setText(String.valueOf(myFormatter.format(bacPercent)));

                        progressBar.setProgress((int)(totalbac/0.25));

                        yourStatus(bacPercent);



                    }

            }

        });


        //RadioGroup and RadioButton

        radioGroup= findViewById(R.id.radioGroupId);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                 int checkedRadioButtonId=radioGroup.getCheckedRadioButtonId();
                Log.d("demo",String.valueOf(checkedRadioButtonId));

                 // RadioButton radioButton=findViewById(checkedRadioButtonId);
                //String selectedButton=radioButton.getText().toString();
                if(checkedRadioButtonId==R.id.radioButton1ozId)
                {
                    drinkSize=1;
                }
                else if(checkedRadioButtonId==R.id.radioButton5ozId)
                {
                    drinkSize=5;
                }
                else if(checkedRadioButtonId==R.id.radioButton12ozid) {
                    drinkSize = 12;
                }
                else {
                    drinkSize = 1;
                }
            }

        });


        alcoholValue=findViewById(R.id.alcoholValueId);

        seekBar=findViewById(R.id.alcoholSeekBarId);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){


            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }



            double ourStep = 5;


            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                i=((int)Math.round(i/ourStep))*5;
                String s=i+"%";
                seekBar.setProgress(i);
                alcoholValue.setText(s);




            }
        });


        bacLevelResult=findViewById(R.id.bacLevelResultId);
        progressBar=findViewById(R.id.progressBarId);


        addDrink=findViewById(R.id.addDrinkButtonId);
        addDrink.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {
            if(editText_weight.getText().toString().trim().length()<=0)
            {
                editText_weight.requestFocus();
                editText_weight.setError("Enter the weight in lbs");
            }
            else if(weightInput==0.0) {
                Toast.makeText(MainActivity.this, "Please save your weight and gender", Toast.LENGTH_SHORT).show();
            }

                else{

                        int getAlcoholValue = Integer.parseInt(alcoholValue.getText().toString().split("%")[0]);
                        //Log.d("demo",String.valueOf(drinkSize));
                        alcoholConsumed = (drinkSize * getAlcoholValue);

                        latestBACValue = ((alcoholConsumed) * 6.24) / (weightInput * genderConstant);
                        totalbac = totalbac + latestBACValue;

                        bacPercent = totalbac / 100;

                        alcoholConsumed = (totalbac * weightInput * genderConstant) / 6.24;

                        bacLevelResult.setText(String.valueOf(myFormatter.format(bacPercent)));

                        progressBar.setProgress((int) (totalbac / 0.25));


                        yourStatus(bacPercent);


                    }

    }
});

        Button resetButton=findViewById(R.id.resetButtonId);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText_weight.setText("");

                genderSwitch.setTextOn("F");
                totalbac=0.0;
                alcoholConsumed=0.0;
                weightInput=0.0;

                bacLevelResult.setText(String.valueOf(0.00));
                progressBar.setProgress((int)(totalbac/0.25));

                status.setText("");
                status.setBackgroundColor(Color.TRANSPARENT);

                radioGroup.check(R.id.radioButton1ozId);
                if(saveButton.isEnabled()==false)
                    saveButton.setEnabled(true);

                if(addDrink.isEnabled()==false)
                    addDrink.setEnabled(true);

                seekBar.setProgress(5);



            }
        });





    }
}

