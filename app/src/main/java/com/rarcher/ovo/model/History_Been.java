package com.rarcher.ovo.model;

/**
 * Created by 25532 on 2019/4/1.
 */

public class History_Been {
    String context;
    String year;
    String mouth;
    String day;
    String name;

    public String getContext() {
        return context;
    }

    public String getYear() {
        return year;
    }

    public String getMouth() {
        return mouth;
    }

    public String getDay() {
        return day;
    }

    public String getName() {
        return name;
    }

    public History_Been(String context, String year, String mouth, String day, String name) {

        this.context = context;
        this.year = year;
        this.mouth = mouth;
        this.day = day;
        this.name = name;
    }
}
