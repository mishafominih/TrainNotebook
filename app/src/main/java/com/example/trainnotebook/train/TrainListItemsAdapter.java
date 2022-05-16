package com.example.trainnotebook.train;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

public class TrainListItemsAdapter extends ArrayAdapter<TrainItem> {
    protected static LayoutInflater mInflater;

    public enum RowType {
        HEADER_ITEM, TEXT_ITEM
    }

    public TrainListItemsAdapter(Context context, List<TrainItem> items) {
        super(context, 0, items);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getViewTypeCount() {
        return RowType.values().length;
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getViewType();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getItem(position).getView(mInflater, position, convertView);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public boolean isEnabled(int position) {
        return getItem(position).isEnable();
    }
}

