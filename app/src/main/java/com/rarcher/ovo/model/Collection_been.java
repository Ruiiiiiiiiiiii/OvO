package com.rarcher.ovo.model;

/**
 * Created by 25532 on 2019/4/2.
 */

public class Collection_been {
    String context;
    String author;
    int imageid;

    public String getContext() {
        return context;
    }

    public String getAuthor() {
        return author;
    }

    public int getImageId() {
        return imageid;
    }

    public Collection_been(String context, String author, int imageid) {

        this.context = context;
        this.author = author;
        this.imageid = imageid;
    }
}
