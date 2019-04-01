package com.rarcher.ovo.model;

/**
 * Created by 25532 on 2019/4/1.
 */
import android.os.Parcel;
import android.os.Parcelable;

public class Item {
    String model;//模式
    String good;//点赞
    String title;//标题
    String uid;//唯一标识
    String context;//内容
    String id;//id
    String readcount;//阅读
    String Comment;//评论
    String type;

    public String getType() {
        return type;
    }

    int imageId;//图片id

    public String getModel() {
        return model;
    }

    public String getGood() {
        return good;
    }

    public String getTitle() {
        return title;
    }

    public String getUid() {
        return uid;
    }

    public String getContext() {
        return context;
    }

    public String getId() {
        return id;
    }

    public String getReadcount() {
        return readcount;
    }

    public String getComment() {
        return Comment;
    }

    public int getImageId() {
        return imageId;
    }

    public Item(String model, String good, String title, String uid, String context, String id, String readcount, String comment,String type, int imageId) {

        this.model = model;
        this.good = good;
        this.title = title;
        this.uid = uid;
        this.context = context;
        this.id = id;
        this.readcount = readcount;
        Comment = comment;
        this.type = type;
        this.imageId = imageId;
    }
}
