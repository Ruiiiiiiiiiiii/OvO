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
import java.util.ArrayList;
import java.util.List;

public class History extends AppCompatActivity {
    private ViewPager mViewPager;
    private LocalDB localDB;
    private CardPagerAdapter mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;

    List<History_Been> history = new ArrayList<>();

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
    public  void  query_date(LocalDB dbhelper){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        Cursor cursor = db.query("History", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String date = cursor.getString(cursor.getColumnIndex("time"));
                String context = cursor.getString(cursor.getColumnIndex("context"));
                mCardAdapter.addCardItem(new CardItem(date,context));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
    }
}
