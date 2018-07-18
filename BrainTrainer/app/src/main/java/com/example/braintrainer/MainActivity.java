package com.example.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.GridLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    boolean lock=false;
    Random r;
    Button playButton;
    int num1,num2;
    int answerloc;
    int correctAnswer;
    int questionNUmber;
    boolean timeout=false;
    TextView score;
    CountDownTimer timer;


    public void finish()
    {
        score=(TextView)findViewById(R.id.scoreTextView);
        score.setText("Score:"+Integer.toString(correctAnswer));
        playButton=(Button)findViewById(R.id.playButton);
        score.setVisibility(View.VISIBLE);
        lock=false;
        playButton.setVisibility(View.VISIBLE);
        timeout=true;
        timer.cancel();

    }


    public void nextChallenge(View view)
    {
        if(questionNUmber<=19 && !timeout) {
            TextView selected = (TextView) view;
            int index = Integer.parseInt((String) selected.getTag());
            if (index == answerloc) {
                correctAnswer++;
            }
            questionNUmber++;
            setNumbers();
            TextView questionText=(TextView)findViewById(R.id.questionTextView2);
            questionText.setText(String.valueOf(questionNUmber)+"/20");
        }
        if(questionNUmber==20)
        {
            playButton=findViewById(R.id.playButton);
            playButton.setVisibility(View.VISIBLE);
            finish();
        }




    }

    public void setNumbers()
    {
        r=new Random();
        num1=r.nextInt(50)+10;
        num2=r.nextInt(50)+10;
        TextView numberText=findViewById(R.id.numberTextView);
        numberText.setText(String.valueOf(num1)+"+"+String.valueOf(num2));
        Log.i("num1,num2",String.valueOf(num1)+","+String.valueOf(num2));



        TextView numberText1=findViewById(R.id.numberText1);
        TextView numberText2=findViewById(R.id.numberText2);
        TextView numberText3=findViewById(R.id.numberText3);
        TextView numberText4=findViewById(R.id.numberText4);
        answerloc=r.nextInt(3)+1;
        Log.i("answer",String.valueOf(num1+num2));
        Log.i("answerloc",Integer.toString(answerloc));


        GridLayout myGrid=(GridLayout)findViewById(R.id.numberGrid);

        for(int i=0; i<myGrid.getChildCount(); i++) {
            int answer=num1+num2;
            TextView child = (TextView)myGrid.getChildAt(i);
            int tag=Integer.parseInt((String)child.getTag());
            if(tag==answerloc)
            {
                child.setText(Integer.toString(answer));
            }
            else
            {
                int anotherAnswer=answer+r.nextInt(10)+(-1*r.nextInt(15));
                child.setText(String.valueOf(anotherAnswer));
            }
        }


    }

    public void play(View view) {
        if(!lock) {

            lock=true;
            r=new Random();
            setNumbers();
            timeout=false;
            correctAnswer=0;
            questionNUmber=0;
            score=(TextView)findViewById(R.id.scoreTextView);
            score.setVisibility(View.INVISIBLE);

            playButton=(Button)findViewById(R.id.playButton);


            playButton.setVisibility(View.INVISIBLE);
            playButton.setText("Play Again!");



            final TextView timerText = findViewById(R.id.timerTextView);
            timer = new CountDownTimer(30100, 1000) {
                @Override
                public void onTick(long l) {

                    timerText.setText(String.valueOf(l / 1000) + "s");

                }

                @Override
                public void onFinish() {


                    finish();



                }
            }.start();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        correctAnswer=0;
        questionNUmber=0;






    }
}
