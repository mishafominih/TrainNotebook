package com.example.trainnotebook.train;

import android.view.LayoutInflater;
import android.view.View;

public class SubItem  implements TrainItem {
    @Override
    public int getViewType() {
        return TrainListItemsAdapter.RowType.TEXT_ITEM.ordinal();
    }

    @Override
    public View getView(LayoutInflater inflater, int position, View convertView) {
        return convertView;
    }

    @Override
    public long getId() {
        return 1;
    }

    @Override
    public boolean isEnable() {
        return true;
    }
}
