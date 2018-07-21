package com.akai.fitnass.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

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
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Workout currentItem = getItem(position);
        viewHolder.itemName.setText(currentItem.getDate());
        return convertView;
    }

    private class ViewHolder {
        TextView itemName;

        ViewHolder(View view) {
            itemName = view.findViewById(android.R.id.text1);
        }
    }
}
