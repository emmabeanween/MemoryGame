package com.example.emmagoldberg.memorygame;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreScreen extends AppCompatActivity {

    private TextView mMatchTextView;
    private Button mPlayAgainButton;

    public static Intent wordIntent(Context packageContext, int score){

        Intent i = new Intent(packageContext, ScoreScreen.class);
        i.putExtra("user_score", score);
        return i;

    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_screen);


        mMatchTextView = (TextView)findViewById(R.id.score_text_view_matches);

        int userScore = getIntent().getIntExtra("user_score", 0);

        mMatchTextView.setText(String.valueOf(userScore/2));



        mPlayAgainButton = (Button)findViewById(R.id.play_again_button);
        mPlayAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent startOver = new Intent(ScoreScreen.this, MemoryGame.class);
                startActivity(startOver);


            }
        });










    }
}
