package com.rarcher.ovo.model;

/**
 * Created by 25532 on 2019/4/2.
 */

public class Details_static {
    public static String context;
    public static String author;
    public static int sourse;

    public static String getContext() {
        return context;
    }

    public static String getAuthor() {
        return author;
    }

    public static int getSourse() {
        return sourse;
    }

    public static void setContext(String context) {

        Details_static.context = context;
    }

    public static void setAuthor(String author) {
        Details_static.author = author;
    }

    public static void setSourse(int sourse) {
        Details_static.sourse = sourse;
    }
}
