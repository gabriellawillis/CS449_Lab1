package com.example.cs449_lab1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    static final private String TAG = "Umpire Buddy v1.0";

    private int strike_count = 0;
    private int ball_count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Starting onCreate...");
        setContentView(R.layout.activity_main);

        View StrikeButton = findViewById(R.id.strike_button);
        StrikeButton.setOnClickListener(this);

        updateStrikeCount();

        View BallButton = findViewById(R.id.ball_button);
        BallButton.setOnClickListener(this);

        updateBallCount();
    }

    private void updateStrikeCount() {
        TextView t = (TextView)findViewById(R.id.strike_count_value);
        t.setText(Integer.toString(strike_count));
    }

    private void updateBallCount() {
        TextView t = (TextView)findViewById(R.id.ball_count_value);
        t.setText(Integer.toString(ball_count));
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.strike_button:
                // Start count over if user tries to increment beyond 2.
                if (strike_count == 2) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Strike Out");
                    builder.setMessage("Batter is out!");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Next Batter", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            strike_count = 0;
                            updateStrikeCount();

                            ball_count = 0;
                            updateBallCount();
                        }
                    });
                    builder.show();
                } else {
                    strike_count++;
                }
                break;
            case R.id.ball_button:
                // Start count over if user tries to increment beyond 3.
                if (ball_count == 3) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Walk");
                    builder.setMessage("Batter walks!");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Next Batter", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            strike_count = 0;
                            updateStrikeCount();

                            ball_count = 0;
                            updateBallCount();
                        }
                    });
                    builder.show();
                } else {
                    ball_count++;
                }
                break;
        }
        updateStrikeCount();
        updateBallCount();

        Button exit_button = (Button) findViewById(R.id.exit_button);
        exit_button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
                System.exit(0);
            }
        });
    }
}