package com.rarcher.ovo.Activities;

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
        mCardAdapter.addCardItem(new CardItem(R.string.title_1, R.string.text_1));
        mCardAdapter.addCardItem(new CardItem(R.string.title_2, R.string.text_1));
        mCardAdapter.addCardItem(new CardItem(R.string.title_3, R.string.text_1));
        mCardAdapter.addCardItem(new CardItem(R.string.title_4, R.string.text_1));

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
}
