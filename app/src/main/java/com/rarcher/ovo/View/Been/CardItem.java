package com.rarcher.ovo.View.Been;

/**
 * Created by 25532 on 2019/4/1.
 */

public class CardItem {

    private String mTextResource;
    private String mTitleResource;

    public CardItem(String title, String text) {
        mTitleResource = title;
        mTextResource = text;
    }

    public String getText() {
        return mTextResource;
    }

    public String getTitle() {
        return mTitleResource;
    }
}

