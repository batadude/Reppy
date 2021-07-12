package com.example.hackathonretry;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RepsScreen extends AppCompatActivity {

    public int numReps = 0, numSets = 0, numRepsLimit = 0, points = 0;
    public boolean isCounting = true;
    public String text;
    public static int lifetimeReps;

    private boolean isMoving = false;
    //public float[] times = new float[MainActivity.numSets];
    public float totalTime = 0;
    private int j;

    private float[] mGravity;
    private float mAccel;
    private float mAccelCurrent;
    private float mAccelLast;
    public int count;

    public Button doneButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reps_screen);

        //this is to make sure this screen gets information
        //these are info from the main screen
        if (getIntent().hasExtra("com.example.hackathonretry.SOMETHING")) {
            TextView tv = (TextView) findViewById(R.id.textView2);
            text = getIntent().getExtras().getString("com.example.hackathonretry.SOMETHING");
            tv.setText("/" + text);
        }
        if (getIntent().hasExtra("com.example.hackathonretry.workoutName")) {
            TextView tv = (TextView) findViewById(R.id.workoutName);
            String name = getIntent().getExtras().getString("com.example.hackathonretry.workoutName");
            tv.setText(name);
        }

        //to put out information/go to different screens
        doneButton = (Button)findViewById(R.id.doneButton);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), FinishScreen.class);
                //what to pass information to another activity
                String k = String.valueOf(j);
                startIntent.putExtra("com.example.hackathonretry.DONE", k);
                startIntent.putExtra("com.example.hackathonretry.LIMIT", text);
                startActivity(startIntent);
            }
        });

        //menu
        Button buttonMenu = (Button)findViewById(R.id.buttonQuit);
        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toMenu(); //this goes to the menu screen
            }
        });

    }

    

    //to count each rep and set it to screen
    public void rep(View view) {
        numReps++;
        numRepsLimit = Integer.parseInt(text);
        TextView reps = (TextView) findViewById(R.id.labelNumReps);

        //counting the reps
        if (numReps <= numRepsLimit) {
            //to get rid of the zero
            if (numReps < 10) {
                reps.setText("0" + numReps);
            } else {
                reps.setText("" + numReps);
            }
        }

        if(numReps == numRepsLimit){
            isCounting = false;
            numSets++;
            numReps = 0;
            j++;
            doneButton.performClick();
        }

    }

    public void toMenu() {
        Intent menu = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(menu);
    }
}



