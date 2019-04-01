package com.rarcher.ovo.View.Adapter;

import com.rarcher.ovo.R;
import com.rarcher.ovo.model.Item;

import java.util.ArrayList;

/**
 * Created by 25532 on 2019/4/1.
 */

public class DataUtils {
    public static ArrayList<Item> getDatas() {
        ArrayList<Item> itemlist = new ArrayList<>();
        itemlist.add(
                new Item("5","2","喵喵喵","123","咕咕咕","123","2", "1","Read The World",false,R.drawable.you_define_me));
        itemlist.add(
                new Item("5","2","咕咕咕","456","喵喵喵","figure","2", "1","时间旅行者",true,R.drawable.cherry));
        return itemlist;
    }
}
