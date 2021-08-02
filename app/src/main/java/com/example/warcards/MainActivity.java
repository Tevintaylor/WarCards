package com.example.warcards;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.io.File;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity implements WinnerDialog.WinnerDialogListener {

    Button dealButton;
    TextView playerScore, tiesScore, cpuScore;
    ImageView firstCard, secondCard;

    int rightNumber = 0;
    int leftNumber = 0;
    int ties = 0;


    //  variable to store the name of the winner
    String Winner = "";
    String WinnerTitleBar = "";
    final Random myRandom = new Random();

    //  array to store the images in.
    int[] images = {R.drawable.card2, R.drawable.card3, R.drawable.card4, R.drawable.card5, R.drawable.card6, R.drawable.card7,
            R.drawable.card8, R.drawable.card9, R.drawable.card10, R.drawable.card11, R.drawable.card12, R.drawable.card13, R.drawable.card14};

    //  incrementer for traversing and getting elements in the list.
    int current_image1 = 0;
    int current_image2 = 0;

    //  variable to store the higest score in each game.
    int numberOfplays = 0;

    //  Media files for sound effects in android studio.
    private SoundPool soundPool;
    private int deal,loose,win,looseCard,winCard,tie,winCard2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dealButton = findViewById(R.id.dealButton);
        playerScore = findViewById(R.id.playerScore);
        tiesScore = findViewById(R.id.tiesScore);
        cpuScore = findViewById(R.id.cpuScore);
        firstCard = findViewById(R.id.firstCard);
        secondCard = findViewById(R.id.secondCard);



//        creating soundpool constructor
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setMaxStreams(1)
                    .setAudioAttributes(audioAttributes)
                    .build();
        }else{
            soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        }

//      setting up sounds to be played
        deal = soundPool.load(this,R.raw.deal,1);
        loose = soundPool.load(this,R.raw.loose,1);
        win = soundPool.load(this,R.raw.win,1);
        looseCard = soundPool.load(this,R.raw.loose_card,1);
        tie = soundPool.load(this,R.raw.tie,1);
        winCard2 = soundPool.load(this,R.raw.win_card_two,1);
        winCard = soundPool.load(this,R.raw.win_card,1);

//        soundPool.play(winCard2, 1.0f, 1.0f, 1, 0, 1.0f);
//        soundPool.play(deal, 1.0f, 1.0f, 1, 0, 1.0f);

            System.out.println(numberOfplays);
//          onclick listener for the deal button
            dealButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    numberOfplays++;
                    if (numberOfplays > 20){
                        openDialog();
//                      put sound here for winner
                        setWinnerInfo();
                    }else{
                        Toast.makeText(getApplicationContext(),"hasn't reach 10 times",Toast.LENGTH_LONG);
                        deal();
//                  put sound here for button click
                    }
                }
            }); // end of onclick listener
    }

        public void deal(){
                    current_image1 = myRandom.nextInt(12);
                    current_image2 = myRandom.nextInt(12);

//                  setting the left snd right image view to a image base on the images array list
                    firstCard.setImageResource(images[current_image1]);
                    secondCard.setImageResource(images[current_image2]);

//                  if statement that controls the scoring based on the value randomly chosen
                    if (current_image1 > current_image2) {
//                      put a sound here for win score
                        soundPool.play(winCard, 1.0f, 1.0f, 1, 0, 1.0f);
                        rightNumber = rightNumber + 1;
                        playerScore.setText(String.valueOf(rightNumber));
                    } else if (current_image1 < current_image2) {
//                        put sound here for lose score
                        soundPool.play(looseCard, 1.0f, 1.0f, 1, 0, 1.0f);
                        leftNumber = leftNumber + 1;
                        cpuScore.setText(String.valueOf(leftNumber));
                    } else {
//                      pust sound here for ties
                        soundPool.play(tie, 1.0f, 1.0f, 1, 0, 1.0f);
                        ties = ties + 1;
                        tiesScore.setText(String.valueOf(ties));
                    }
    }

    public void openDialog(){
        WinnerDialog winnerDialog = new WinnerDialog();
        winnerDialog.show(getSupportFragmentManager(), "Winner Dialog");
    }

//  unused function for now
    @Override
    public void applyTexts(String winnerName) {
    }

//  Function to reset all the value and start thr game from the beginning
    @Override
    public void reset() {
        numberOfplays = 0;
        playerScore.setText(String.valueOf(0));
        cpuScore.setText(String.valueOf(0));
        tiesScore.setText(String.valueOf(0));
        rightNumber = 0;
        leftNumber = 0;
        ties = 0;
    }

//  Function to set winner information
    public void setWinnerInfo(){
//      if statement yto check the highest score of each match and congratulate the respective winner.
        if(rightNumber >leftNumber){
            System.out.println("Player Wins");
            soundPool.play(win, 1.0f, 1.0f, 1, 0, 1.0f);
            Winner = "Scores \n Player:"+rightNumber+"   CPU: "+leftNumber+"   Ties: "+ties;
            WinnerTitleBar = "Player";
        }else if(rightNumber<leftNumber){
            System.out.println("Cpu wins");
            soundPool.play(loose, 1.0f, 1.0f, 1, 0, 1.0f);
            Winner = "Scores \n Player:"+rightNumber+"   CPU: "+leftNumber+"   Ties: "+ties;
            WinnerTitleBar = "CPU";
        }else{
            System.out.println("Both Tied");
            soundPool.play(winCard2, 1.0f, 1.0f, 1, 0, 1.0f);
            Winner = "(IT WAS A TIE)\nScores \n Player: "+rightNumber+"   CPU: "+leftNumber+"   Ties: "+ties;
            WinnerTitleBar = "No one";
        }
    }

//  FUNCTION TO SEND THE NAME OF THE WINNER TO THE POP UP SCREEN
    @Override
    public String winner_Name() {
        return Winner;
    }

    @Override
    public String Title_Name() {
        return "Winner is "+WinnerTitleBar;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundPool.release();
        soundPool=null;
    }
}
