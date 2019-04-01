package com.rarcher.ovo.View.Adapter;

/**
 * Created by 25532 on 2019/4/1.
 */
import android.support.v7.widget.CardView;

public interface CardAdapter {

    int MAX_ELEVATION_FACTOR = 8;

    float getBaseElevation();

    CardView getCardViewAt(int position);

    int getCount();
}
