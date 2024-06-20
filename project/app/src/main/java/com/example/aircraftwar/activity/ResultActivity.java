package com.example.aircraftwar.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.aircraftwar.R;
import com.example.aircraftwar.anim.NumAnim;

public class ResultActivity extends AppCompatActivity {
    private TextView tvTitle1;
    private TextView tvTitle2;
    private TextView tvScore1;
    private TextView tvScore2;
    private TextView tvResult;
    private Button btnExit;
    private int score;
    private int opScore;
    private String loseText = "You Lose!";
    private String winText = "You Win!";
    private String drawText = "Worthy Opponent!";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        score = getIntent().getIntExtra("score",0);
        opScore = getIntent().getIntExtra("opScore",0);

        initView();
        NumAnim.startAnim(tvScore1, score, 2500);     //设置滚动动画持续1秒
        NumAnim.startAnim(tvScore2, opScore, 2500);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(ResultActivity.this, MainActivity.class);
               startActivity(intent);
               finish();
            }
        });

    }

    private void initView(){
        tvTitle1 = (TextView) findViewById(R.id.tvTitle1);
        tvTitle2 = (TextView) findViewById(R.id.tvTitle2);
        tvScore1 = (TextView) findViewById(R.id.tvScore1);
        tvScore2 = (TextView) findViewById(R.id.tvScore2);
        tvResult = (TextView) findViewById(R.id.tvResult);
        btnExit = (Button) findViewById(R.id.btnExit);
        if(score < opScore){
            tvResult.setText(loseText);
        }
        else if(score > opScore){
            tvResult.setText(winText);
        }
        else tvResult.setText(drawText);
    }
}