package com.rarcher.ovo.Activities;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.rarcher.ovo.R;
import com.rarcher.ovo.Utils.tools.RxBus;
import com.rarcher.ovo.View.Adapter.DataUtils;
import com.rarcher.ovo.View.Adapter.VerticalPagerAdapter;
import com.rarcher.ovo.View.Fragment.LeftMenuFragment;
import com.rarcher.ovo.View.Fragment.RightMenuFragment;
import com.rarcher.ovo.model.Event;
import com.rarcher.ovo.model.Item;
import com.rarcher.ovo.widget.OnViewPagerListener;
import com.rarcher.ovo.widget.PagerLayoutManager;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {

    private long mLastClickTime;
    private SlidingMenu slidingMenu;
    private Subscription subscription;
    private LeftMenuFragment leftMenu;
    private RightMenuFragment rightMenu;
    private ArrayList<Item> mDatas = new ArrayList<>();
    private RecyclerView recyclerView;
    private VerticalPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mian);
        ButterKnife.bind(this);
        recyclerView = findViewById(R.id.recycler_view);
        initMenu();
        PagerLayoutManager mLayoutManager = new PagerLayoutManager(this, OrientationHelper.VERTICAL);

        //TODO:
        mDatas.addAll(DataUtils.getDatas());
        mAdapter = new VerticalPagerAdapter(this, mDatas);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);





        mLayoutManager.setOnViewPagerListener(new OnViewPagerListener() {
            @Override
            public void onInitComplete(View view) {
            }

            @Override
            public void onPageSelected(int position, boolean isBottom, View view) {
            }

            @Override
            public void onPageRelease(boolean isNext, int position, View view) {
            }
        });
    }

    private void initMenu() {
        slidingMenu = new SlidingMenu(this);
        slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);
        // 设置触摸屏幕的模式
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        // 设置渐入渐出效果的值
        slidingMenu.setFadeDegree(0.35f);
        slidingMenu.setFadeEnabled(true);
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        slidingMenu.setMenu(R.layout.left_menu);
        leftMenu = new LeftMenuFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.left_menu, leftMenu).commit();
        slidingMenu.setSecondaryMenu(R.layout.right_menu);
        rightMenu = new RightMenuFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.right_menu, rightMenu).commit();
        subscription = RxBus.getInstance().toObservable(Event.class)
                .subscribe(new Action1<Event>() {
                    @Override
                    public void call(Event event) {
                        slidingMenu.showContent();
                    }
                });
    }

    @Override
    public void onBackPressed() {
        if (slidingMenu.isMenuShowing() || slidingMenu.isSecondaryMenuShowing()) {
            slidingMenu.showContent();
        } else {
            if (System.currentTimeMillis() - mLastClickTime <= 2000L) {
                super.onBackPressed();
            } else {
                mLastClickTime = System.currentTimeMillis();
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @OnClick({R.id.left_slide, R.id.right_slide})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.left_slide:
                slidingMenu.showMenu();
                leftMenu.startAnim();
                break;
            case R.id.right_slide:
                slidingMenu.showSecondaryMenu();
                rightMenu.startAnim();
                break;
        }
    }


    @Override
    protected void onDestroy() {
        if (subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        super.onDestroy();
    }
}
