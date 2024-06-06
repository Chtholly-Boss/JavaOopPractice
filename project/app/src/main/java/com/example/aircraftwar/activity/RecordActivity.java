package com.example.aircraftwar.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import com.example.aircraftwar.R;
import com.example.aircraftwar.adapter.RecordAdapter;
import com.example.aircraftwar.adapter.RecordBean;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import kotlin.text._OneToManyTitlecaseMappingsKt;

public class RecordActivity extends AppCompatActivity implements View.OnClickListener{
    private String fName ;

    private int gameType;
    private String time;
    private int score;

    private Button btnExit;

    private TextView tvRTitle;
    private ListView listView;
    private List<RecordBean> records = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        if (getIntent() != null) {
            gameType = getIntent().getIntExtra("gameType", 1);
            time = getIntent().getStringExtra("time");
            score = getIntent().getIntExtra("score", 0);
        }
        switch (gameType){
            case 1:
                fName = "EasyGameRecord.txt";
                break;
            case 2:
                fName = "MidGameRecord.txt";
                break;
            case 3:
                fName = "HardGameRecord.txt";
                break;
        }
        try {
            records = readFile(fName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        records.add(new RecordBean("test",score,time));
        records = setRank(records);
        initView();

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(RecordActivity.this);
                builder.setTitle("Delete").setMessage("Really delete the record？(Permanently!)").setPositiveButton("Yes!",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                boolean removed = false;
                                for (RecordBean rc : records) {
                                    if (rc.getRank() == position + 1) {
                                        records.remove(rc); // 删除元素
                                        removed = true;
                                        break;
                                    }
                                }
                                if (removed) {

                                    records = setRank(records); // 重新设置排名
                                    listView.setAdapter(new RecordAdapter(RecordActivity.this, records)); // 更新适配器
                                }
                            }
                        });
                builder.setNegativeButton("Never Mind", null);
                builder.create().show();

                return false; // 没有找到匹配项，事件未被处理
            }
        });

    }
    private void initView(){
        btnExit = (Button) findViewById(R.id.btnExit);
        tvRTitle = (TextView) findViewById(R.id.tvRTitle);
        switch (gameType){
            case 1:
                tvRTitle.setText("Easy Mode Records");
                break;
            case 2:
                tvRTitle.setText("Mid Mode Records");
                break;
            case 3:
                tvRTitle.setText("Hard Mode Records");
                break;
        }
        listView = findViewById(R.id.LV);

        listView.setAdapter(new RecordAdapter(RecordActivity.this, records));
        btnExit.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(RecordActivity.this);
        builder.setTitle("Save & Exit").setMessage("Save the changes and exit?").setPositiveButton("Yes!",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        try {
                            writeFile(fName);
                            Intent intent = new Intent(RecordActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    }
                });
        builder.setNegativeButton("Don't Save", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(RecordActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.create().show();


    }
    public List<RecordBean> readFile(String strFilePath) throws IOException {
        List<RecordBean> recordList = new ArrayList<>();
        File f = new File(getFilesDir(), strFilePath);
        if(!f.exists()){
            f.createNewFile();
        }
        FileInputStream fis = openFileInput(strFilePath);
        StringBuffer sBuffer = new StringBuffer();
        DataInputStream dataIO = new DataInputStream(fis);
        String line = null;
        while((line =  dataIO.readLine()) != null) {
            String row[] = line.split(",");
            RecordBean record = new RecordBean(row[0],Integer.parseInt(row[1]),row[2]);
            recordList.add(record);
            sBuffer.append(line + "\n");
        }
        dataIO.close();
        fis.close();
        return recordList;
    }


    public List<RecordBean> setRank(List<RecordBean> recordList){
        recordList = recordList.stream().sorted(Comparator.comparing(RecordBean::getScore).reversed()).collect(Collectors.toList());
        int rank = 1;
        for(RecordBean rc : recordList){
            rc.setRank(rank);
            rank++;
        }
        return recordList;

    }
    public void writeFile(String strFilePath) throws IOException {

        records = setRank(records);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = openFileOutput(strFilePath, MODE_PRIVATE);
            String line = null;
            for (RecordBean rc : records){
                 line = "test"+","+rc.getScore()+","+rc.getTime()+"\n";
                fileOutputStream.write(line.getBytes());
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Toast.makeText(RecordActivity.this, "Succssesfully Saved", Toast.LENGTH_LONG).show();
    }
}