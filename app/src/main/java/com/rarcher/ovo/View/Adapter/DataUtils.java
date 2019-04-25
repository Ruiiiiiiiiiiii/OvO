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


        //TODO:

        itemlist.add(
                new Item("5", "2", "留住真实的自己", "123","喵喵喵", "123", "2", "1", "Read The World", false, R.drawable.timg, "Rarcher"));



        itemlist.add(
                new Item("5", "2", "咕咕咕", "456", "嘎嘎嘎", "figure", "2", "1", "时间旅行者", true, R.drawable.timg1, "Rarcher"));



        return itemlist;
    }
}
