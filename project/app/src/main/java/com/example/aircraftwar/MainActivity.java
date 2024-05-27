package com.example.aircraftwar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private boolean isSoundEffectOn = false;
    Button btnOffline;

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
        rg = (RadioGroup) findViewById(R.id.rg);
        rbOff = (RadioButton) findViewById(R.id.rbOff);
        rbOn = (RadioButton) findViewById(R.id.rbOn);

        btnOffline.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        int selectedId = rg.getCheckedRadioButtonId();
        if (selectedId == R.id.rbOn){
            isSoundEffectOn = true;
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable("Boolean",isSoundEffectOn);
        Intent intent = new Intent(MainActivity.this, OfflineActivity.class);
        intent.putExtra("sound", bundle);
        startActivity(intent);
    }
}