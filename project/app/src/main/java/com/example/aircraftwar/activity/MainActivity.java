package com.example.aircraftwar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aircraftwar.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private boolean isSoundEffectOn = false;
    Button btnOffline;
    Button btnOnline;

    RadioGroup rg;
    RadioButton rbOn;
    RadioButton rbOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView(){
        btnOffline = (Button) findViewById(R.id.btnOffline);
        btnOnline = (Button) findViewById(R.id.btnOnline);
        rg = (RadioGroup) findViewById(R.id.rg);
        rbOff = (RadioButton) findViewById(R.id.rbOff);
        rbOn = (RadioButton) findViewById(R.id.rbOn);

        btnOffline.setOnClickListener(this);
        btnOnline.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        isSoundEffectOn = rg.getCheckedRadioButtonId() == R.id.rbOn;
        int selectedId = view.getId();
        if (selectedId == R.id.btnOffline) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("Boolean",isSoundEffectOn);
            Intent intent = new Intent(MainActivity.this, OfflineActivity.class);
            intent.putExtra("sound", bundle);
            startActivity(intent);
        } else if (selectedId == R.id.btnOnline) {
            // TODO : Create Child Thread
        }
    }
}