package com.akai.fitnass.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.akai.fitnass.R;
import com.akai.fitnass.db.domain.Workout;

import java.util.ArrayList;

public class DayWorkoutAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Workout> days;

    public DayWorkoutAdapter(Context context, ArrayList<Workout> days) {
        this.context = context;
        this.days = days;
    }

    @Override
    public int getCount() {
        return days.size();
    }

    @Override
    public Workout getItem(int position) {
        return days.get(position);
    }

    @Override
    public long getItemId(int position) {
        return days.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_day, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Workout currentItem = getItem(position);
        viewHolder.id.setText(String.valueOf(currentItem.getId()));
        viewHolder.date.setText(currentItem.getDate());
        return convertView;
    }

    private class ViewHolder {
        private TextView id;
        private TextView date;

        ViewHolder(View view) {
            id = view.findViewById(R.id.id_day);
            date = view.findViewById(R.id.day_date);
        }
    }
}
