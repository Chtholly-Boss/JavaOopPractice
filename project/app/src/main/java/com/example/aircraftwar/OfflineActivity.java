package com.example.aircraftwar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OfflineActivity extends AppCompatActivity {
    private Boolean isSoundEffectOn;
    TextView tvTitle;
    Button btnEasy;
    Button btnNormal;
    Button btnHard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline);

        Intent receiveIntent = getIntent();
        Bundle bundle = receiveIntent.getBundleExtra("sound");
        isSoundEffectOn = (Boolean) bundle.get("Boolean");
        String test = "00000000000000000";

        if(isSoundEffectOn == true) test = "111111111111111";
        initView();

        Toast.makeText(this, test, Toast.LENGTH_LONG).show();


    }

    private void initView(){
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        btnEasy = (Button) findViewById(R.id.btnEasy);
        btnNormal = (Button) findViewById(R.id.btnNormal);
        btnHard = (Button) findViewById(R.id.btnHard);
    }


}