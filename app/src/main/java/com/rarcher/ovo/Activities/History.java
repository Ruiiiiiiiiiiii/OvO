package com.rarcher.ovo.Activities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.rarcher.ovo.DB.LocalDB;
import com.rarcher.ovo.R;
import com.rarcher.ovo.View.Adapter.CardPagerAdapter;
import com.rarcher.ovo.View.Adapter.ShadowTransformer;
import com.rarcher.ovo.View.Been.CardItem;
import com.rarcher.ovo.model.History_Been;

import java.io.ByteArrayInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/*
* 显示历史时光胶囊,注意,初始化的时候检测一下时间,如果是今天的不要加入这个历史列表!!!!!!!
* */
public class History extends AppCompatActivity {
    private ViewPager mViewPager;
    private LocalDB localDB;
    private CardPagerAdapter mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        initDB();

        mViewPager = (ViewPager) findViewById(R.id.viewPager);

        mCardAdapter = new CardPagerAdapter();

        mCardAdapter.addCardItem(new CardItem("时光", "记录美好的过去"));
        query_date(localDB);
        mCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);
        mCardShadowTransformer.enableScaling(true);

        mViewPager.setAdapter(mCardAdapter);
        mViewPager.setPageTransformer(false, mCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(3);
    }
    private void initDB(){
        localDB = new LocalDB(getApplicationContext(),"History.db",null,2);
        localDB.getWritableDatabase();
    }
    public  void  query_date(LocalDB dbhelper) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        Cursor cursor = db.query("History", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String date = cursor.getString(cursor.getColumnIndex("time"));
                String context = cursor.getString(cursor.getColumnIndex("context"));
                //字符串解析为Date对象,用于比较时间
                /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date1 =sdf.parse(date);*/

                /*
                * 其实还有一种方法,直接对字符床进行比较                  √用这个,不需要去处理异常
                * date.compareTo();  使用这个方法,就可以比较出时间  例如:
                *
                String date = "2017-07-17";
                System.out.println("compareToBefore1 : "+date.compareTo("2017-06-16"));
                * */
                mCardAdapter.addCardItem(new CardItem(date,context));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
    }
}
