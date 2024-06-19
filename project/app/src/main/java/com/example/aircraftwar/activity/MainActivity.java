package com.example.aircraftwar.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aircraftwar.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "MainActivity";
    private Socket socket;
    private boolean isSoundEffectOn = false;
    Button btnOffline;
    Button btnOnline;

    RadioGroup rg;
    RadioButton rbOn;
    RadioButton rbOff;
    AlertDialog dialog;

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
        }
        if (selectedId == R.id.btnOnline) {
            displayMatchingDialog();
            new Thread(this::doInBackground).start();

        }
    }
    protected void doInBackground() {
        try {
            socket = new Socket("10.0.2.2",9999);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String msg = in.readLine();
            if ("Matched".equals(msg)) {
                dismissMatchingDialog();
                // TODO : jump to GameActivity
                System.out.println("Sucessfully Matched");
                Intent intent = new Intent(MainActivity.this,GameActivity.class);
                intent.putExtra("gameType",4);
                intent.putExtra("sound",isSoundEffectOn);
                GameActivity.socket = this.socket;
                startActivity(intent);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void displayMatchingDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("");
        builder.setMessage("Matching Opponent For You...");
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.dismiss();
            }
        });
        dialog = builder.create();
        dialog.show();
    }
    private void dismissMatchingDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }
}