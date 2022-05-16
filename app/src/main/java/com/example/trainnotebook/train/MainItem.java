package com.example.trainnotebook.train;

import android.view.LayoutInflater;
import android.view.View;

public class MainItem implements TrainItem{
    @Override
    public int getViewType() {
        return TrainListItemsAdapter.RowType.HEADER_ITEM.ordinal();
    }

    @Override
    public View getView(LayoutInflater inflater, int position, View convertView) {
        return convertView;
    }

    @Override
    public long getId() {
        return 0;
    }

    @Override
    public boolean isEnable() {
        return true;
    }
}
