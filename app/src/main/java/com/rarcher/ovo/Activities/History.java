package com.rarcher.ovo.Activities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rarcher.ovo.R;
import com.rarcher.ovo.View.Adapter.CardPagerAdapter;
import com.rarcher.ovo.View.Adapter.ShadowTransformer;
import com.rarcher.ovo.View.Been.CardItem;

public class History extends AppCompatActivity {
    private ViewPager mViewPager;

    private CardPagerAdapter mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
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
}
