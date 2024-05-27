package com.example.aircraftwar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

<<<<<<< HEAD
public class OfflineActivity extends AppCompatActivity implements View.OnClickListener{
    private Boolean isSoundEffectOn;
    TextView tvTitle;
    Button btnEasy;
    Button btnNormal;
    Button btnHard;
=======
public class OfflineActivity extends AppCompatActivity {
>>>>>>> myb

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline);

        Intent receiveIntent = getIntent();
        Bundle bundle = receiveIntent.getBundleExtra("sound");
        isSoundEffectOn = (Boolean) bundle.get("Boolean");
        if(isSoundEffectOn)Toast.makeText(this, "音效开启", Toast.LENGTH_LONG).show();
        else Toast.makeText(this, "音效关闭", Toast.LENGTH_LONG).show();
        initView();




    }

    private void initView(){
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        btnEasy = (Button) findViewById(R.id.btnEasy);
        btnNormal = (Button) findViewById(R.id.btnNormal);
        btnHard = (Button) findViewById(R.id.btnHard);
        btnEasy.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){



    }

}