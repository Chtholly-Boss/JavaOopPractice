package com.example.aircraftwar.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.aircraftwar.R;

import java.util.List;

public class RecordAdapter extends BaseAdapter {
    Context context;
    List<RecordBean> records;

    public RecordAdapter(Context context, List<RecordBean> records) {
        this.context = context;
        this.records = records;
    }

    @Override
    public int getCount() {
        return records == null? 0 : records.size();
    }

    @Override
    public Object getItem(int i) {
        return getItem(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null){
            view = View.inflate(context, R.layout.adapter_record, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        RecordBean recordBean = (RecordBean) records.get(i);
        viewHolder.tvTime.setText(String.valueOf(recordBean.getTime()));
        viewHolder.tvRank.setText(recordBean.getRank());
        viewHolder.tvScore.setText(String.valueOf(recordBean.getScore()));
        viewHolder.tvName.setText(String.valueOf(recordBean.getName()));
        return view;
    }
    static class ViewHolder {
        View view;
        TextView tvRank;
        TextView tvName;
        TextView tvScore;
        TextView tvTime;


        public ViewHolder(View view) {
            this.view = view;
            this.tvName = (TextView) view.findViewById(R.id.tvName);
            this.tvRank = (TextView) view.findViewById(R.id.tvRank);
            this.tvScore = (TextView) view.findViewById(R.id.tvScore);
            this.tvTime = (TextView) view.findViewById(R.id.tvTime);
        }
    }
}
