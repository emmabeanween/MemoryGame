package com.example.emmagoldberg.memorygame;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class MemoryGame extends AppCompatActivity {

    private String isVisible;


    private GridView mGridView;
    private TextView mTimeLeftText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_game);

        final List<Image> mPicList = new ArrayList<>();
        // add pics

        mPicList.add(new Image(R.drawable.arthur_passengers, false));
        mPicList.add(new Image(R.drawable.arthur_passengers, false));
        mPicList.add(new Image(R.drawable.aurora_passengers, false));
        mPicList.add(new Image(R.drawable.aurora_passengers, false));
        mPicList.add(new Image(R.drawable.jim_passengers, false));
        mPicList.add(new Image(R.drawable.jim_passengers, false));
        mPicList.add(new Image(R.drawable.jim_and_aurora, false));
        mPicList.add(new Image(R.drawable.jim_and_aurora, false));
        mPicList.add(new Image(R.drawable.homestead_passengers, false));
        mPicList.add(new Image(R.drawable.homestead_passengers,false));
        mPicList.add(new Image(R.drawable.passengers_promo, false));
        mPicList.add(new Image(R.drawable.passengers_promo, false));

        Collections.shuffle(mPicList); // shuffle randomly
        final CustomAdapter myAdapter = new CustomAdapter(getApplicationContext(), mPicList);
        mGridView = (GridView) findViewById(R.id.my_grid_view);
        mGridView.setAdapter(myAdapter);


        // start grand countdown timer - amount of time user has to match pairs

         new CountDownTimer(60000, 1000) {
             @Override
             public void onTick(long millisUntilFinished) {

                 long secondsRemaining = millisUntilFinished / 1000;
                 mTimeLeftText = (TextView) findViewById(R.id.time_left_view);
                 mTimeLeftText.setText("time left:" + secondsRemaining);


             }

             @Override
             public void onFinish() {

                 int totalcount = 0;

                 // check if over
                 for (int i = 0; i < mPicList.size(); i++) {
                     // get total number of matches
                     if (mPicList.get(i).getVisibility() == true) {

                         totalcount++;


                     }

                 }


                 Log.i("totalgotten", String.valueOf(totalcount));

                 // pass in total cards gotten to next screen

                 Intent scoreIntent = ScoreScreen.wordIntent(MemoryGame.this, totalcount);
                 startActivity(scoreIntent);



             }
         }.start();



        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                //start timer

                new CountDownTimer(2000, 1000) {

                    public void onTick(long millisUntilFinished) {

                        mPicList.get(position).setVisible(true);
                        myAdapter.refresh(mPicList);

                    }

                    public void onFinish() {

                        // see if two matching images are visible

                        checkMatch(mPicList.get(position).getImage(), mPicList, myAdapter);


                    }

                }.start();




            }
        });





    }


    public void checkMatch(int image, List<Image> ourList, CustomAdapter mAdapter) {
        int count = 0;

        for (int i = 0; i < mGridView.getChildCount(); i++) {

            if (ourList.get(i).getImage() == image && ourList.get(i).getVisibility() == true) {

                count++;
                Log.i("count", String.valueOf(count));
            }


        }

        if (count == 2) {
            // the user matched a pair
            // set both to be visible

            for (int i = 0; i < ourList.size(); i++) {

                if (ourList.get(i).getImage() == image) {

                    ourList.get(i).setVisible(true);
                    mAdapter.refresh(ourList);

                }

            }


        } else {

            // the user didn't match both in time

            for (int i = 0; i < ourList.size(); i++) {

                if (ourList.get(i).getImage() == image) {

                    ourList.get(i).setVisible(false);
                    mAdapter.refresh(ourList);

                }


            }

        }

    }


}








