package com.rarcher.ovo.View.Fragment;


import android.app.usage.UsageEvents;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rarcher.ovo.Activities.History;
import com.rarcher.ovo.R;
import com.rarcher.ovo.Utils.tools.RxBus;
import com.rarcher.ovo.model.Event;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 */
public class LeftMenuFragment extends Fragment {
    @BindView(R.id.right_slide_close)
    ImageView rightSlideClose;
    @BindView(R.id.search)
    ImageView search;
    @BindView(R.id.home_page_tv)
    TextView homePageTv;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;

    private List<View> mViewList = new ArrayList();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_left_menu, container, false);
        ButterKnife.bind(this, view);
        loadView();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void loadView() {
        mViewList.add(homePageTv);

    }

    @OnClick({R.id.right_slide_close, R.id.search, R.id.home_page_tv})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.right_slide_close:
                RxBus.getInstance().postEvent(new Event(1000, "closeMenu"));
                break;
            case R.id.search:
                break;
            case R.id.home_page_tv:
                Toast.makeText(getContext(),"星空",Toast.LENGTH_SHORT).show();
                break;

        }
    }

    public void startAnim() {
        startIconAnim(search);
        startIconAnim(rightSlideClose);
        startColumnAnim();
    }

    private void startColumnAnim() {
        TranslateAnimation localTranslateAnimation = new TranslateAnimation(0F, 0.0F, 0.0F, 0.0F);
        localTranslateAnimation.setDuration(700L);
        for (int j = 0; j < mViewList.size(); j++) {
            View localView = this.mViewList.get(j);
            localView.startAnimation(localTranslateAnimation);
            localTranslateAnimation = new TranslateAnimation(j * -35, 0.0F, 0.0F, 0.0F);
            localTranslateAnimation.setDuration(700L);
        }
    }

    private void startIconAnim(View paramView) {
        ScaleAnimation localScaleAnimation = new ScaleAnimation(0.1F, 1.0F, 0.1F, 1.0F, paramView.getWidth() / 2, paramView.getHeight() / 2);
        localScaleAnimation.setDuration(1000L);
        paramView.startAnimation(localScaleAnimation);
        float f1 = paramView.getWidth() / 2;
        float f2 = paramView.getHeight() / 2;
        localScaleAnimation = new ScaleAnimation(1.0F, 0.5F, 1.0F, 0.5F, f1, f2);
        localScaleAnimation.setInterpolator(new BounceInterpolator());
    }
}