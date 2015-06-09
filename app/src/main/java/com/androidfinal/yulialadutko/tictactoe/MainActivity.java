package com.androidfinal.yulialadutko.tictactoe;

//Yulia Ladutko
//yulialadutko@my.smccd.edu
//CIS 135
//MainActivity.java
//Final
//05/27/2015

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.os.Handler;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements OnClickListener{

    private boolean turn;
    private Button [] buttons;
    private Button button1, button2, button3, button4, button5, button6, button7, button8, button9;
    private Button newGame;
    private TextView turnTextView;
    private int turnCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);

        // get references to the widgets
        turnTextView = (TextView) findViewById(R.id.turnTextView);
        newGame = (Button) findViewById(R.id.newGameButton);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        buttons = new Button[] {
                button1, button2, button3, button4, button5, button6, button7, button8, button9 };

        // set the listeners
        for(Button b: buttons)
            b.setOnClickListener(this);

        turnTextView.setText("Player X's turn");

        newGame.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                turn = true;
                turnCount = 0;
                for (Button b : buttons) {
                    b.setText("");
                    b.setClickable(true);
                    turnTextView.setText("Player X's turn");
                    turn = false;
                }
            }
        });

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getSupportActionBar().hide();
            }
        }, 5000);

        RelativeLayout rl= (RelativeLayout)findViewById(R.id.myRelativeLayout);
        rl.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                boolean showing = getSupportActionBar().isShowing();
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    if (showing) {
                        getSupportActionBar().hide();
                        showing = false;
                    } else {
                        getSupportActionBar().show();
                        showing = true;

                    }
                return false;
            }
        });
    }

    @Override
    public void onConfigurationChanged (Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onClick(View v) {
        buttonClicked(v);
    }

    private void buttonClicked(View v) {
        Button b = (Button) v;
        if (turn) {
            b.setText("O");
            turnTextView.setText("Player X's turn");
        } else {
            b.setText("X");
            turnTextView.setText("Player O's turn");
        }
        turnCount++;
        b.setClickable(false);
        turn = !turn;

        getWinner();
    }

    public void getWinner(){

        boolean winnerFound = false;

        // horizontal:
        if (button1.getText() == button2.getText() && button2.getText() == button3.getText()
                && !button1.isClickable())
            winnerFound = true;
        else if (button4.getText() == button5.getText() && button5.getText() == button6.getText()
                && !button4.isClickable())
            winnerFound = true;
        else if (button7.getText() == button8.getText() && button8.getText() == button9.getText()
                && !button7.isClickable())
            winnerFound = true;

            // vertical:
        else if (button1.getText() == button4.getText() && button4.getText() == button7.getText()
                && !button1.isClickable())
            winnerFound = true;
        else if (button2.getText() == button5.getText() && button5.getText() == button8.getText()
                && !button5.isClickable())
            winnerFound = true;
        else if (button3.getText() == button6.getText() && button6.getText() == button9.getText()
                && !button9.isClickable())
            winnerFound = true;

            // diagonal:
        else if (button1.getText() == button5.getText() && button5.getText() == button9.getText()
                && !button1.isClickable())
            winnerFound = true;
        else if (button3.getText() == button5.getText() && button5.getText() == button7.getText()
                && !button5.isClickable())
            winnerFound = true;

        if (winnerFound) {
            for(Button b: buttons)
                b.setClickable(false);

            if (!turn)
                turnTextView.setText("O wins");
            else
                turnTextView.setText("X wins");
        } else if (turnCount == 9)
            turnTextView.setText("It's a draw!");

    }
    /*@Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        /*for(int i = 0; i < buttons.length; i++) {
            String bName = "b" + i;
            for(Button b: buttons) {
                savedInstanceState.putString(bName, b.getText().toString());
            }
        }

        savedInstanceState.putString("turn", turnTextView.getText().toString());
        savedInstanceState.putString("b1", button1.getText().toString());
        savedInstanceState.putString("b2", button2.getText().toString());
        savedInstanceState.putString("b3", button3.getText().toString());
        savedInstanceState.putString("b4", button4.getText().toString());
        savedInstanceState.putString("b5", button5.getText().toString());
        savedInstanceState.putString("b6", button6.getText().toString());
        savedInstanceState.putString("b7", button7.getText().toString());
        savedInstanceState.putString("b8", button8.getText().toString());
        savedInstanceState.putString("b9", button9.getText().toString());

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }*/

    /*public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        // Restore state members from saved instance
        /*for(int i = 0; i < buttons.length; i++) {
            String bName = "b" + i;
            for(Button b: buttons) {
                b.setText(savedInstanceState.getString(bName));
            }
        }
        turnTextView.setText(savedInstanceState.getString("turn"));
        button1.setText(savedInstanceState.getString("b1"));
        button2.setText(savedInstanceState.getString("b2"));
        button3.setText(savedInstanceState.getString("b3"));
        button4.setText(savedInstanceState.getString("b4"));
        button5.setText(savedInstanceState.getString("b5"));
        button6.setText(savedInstanceState.getString("b6"));
        button7.setText(savedInstanceState.getString("b7"));
        button8.setText(savedInstanceState.getString("b8"));
        button9.setText(savedInstanceState.getString("b9"));
    }*/
}




