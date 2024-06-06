package com.example.aircraftwar.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aircraftwar.R;
import com.example.aircraftwar.adapter.RecordAdapter;
import com.example.aircraftwar.adapter.RecordBean;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RecordActivity extends AppCompatActivity {
    String fName = "record.txt";
    private ListView listView;
    private List<RecordBean> records = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        records = setRank(readFile(fName));
        
        listView = findViewById(R.id.LV);
        listView.setAdapter(new RecordAdapter(RecordActivity.this, records));
    }

    public List<RecordBean> readFile(String strFilePath){
        List<RecordBean> recordList = new ArrayList<>();
        File file = new File(strFilePath);
        if (file.isDirectory()){

        }else{
            try{
                InputStream instream = new FileInputStream(file);
                if (instream != null){
                    InputStreamReader inputReader = new InputStreamReader(instream);
                    BufferedReader br = new BufferedReader(inputReader);
                    String line;
                    //逐行读取
                    while (( line = br.readLine()) != null){
                        String row[] = line.split(",");
                        RecordBean record = new RecordBean(row[0],Integer.valueOf(row[1]),row[2]);
                        recordList.add(record);
                    }
                    instream.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
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
        BufferedWriter bw = new BufferedWriter(new FileWriter(strFilePath)) ;
        for (RecordBean rc : records){
             String line = rc.getName()+","+rc.getScore()+","+rc.getTime();
             bw.write(line+"\n");
        }
        bw.flush();
        bw.close();
    }
}