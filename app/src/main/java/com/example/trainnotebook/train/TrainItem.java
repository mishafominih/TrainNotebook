package com.example.trainnotebook.train;

import android.view.LayoutInflater;
import android.view.View;

public interface TrainItem {
    public int getViewType();
    public View getView(LayoutInflater inflater, int position, View convertView);
    public long getId();
    public boolean isEnable();
}
