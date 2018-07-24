package com.akai.fitnass.ui.day;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.akai.fitnass.R;

import java.util.ArrayList;

public class WorkoutAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<String> mLaps;

    WorkoutAdapter(Context context, ArrayList<String> laps) {
        mContext = context;
        mLaps = laps;
    }

    @Override
    public int getCount() {
        return mLaps.size();
    }

    @Override
    public String getItem(int i) {
        return mLaps.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_workout, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.id.setText(String.valueOf(i+1));
        viewHolder.lap.setText(getItem(i));
        return view;
    }

    private class ViewHolder {
        private TextView id;
        private TextView lap;

        ViewHolder(View view) {
            id = view.findViewById(R.id.id_lap);
            lap = view.findViewById(R.id.lap);
        }
    }
}
