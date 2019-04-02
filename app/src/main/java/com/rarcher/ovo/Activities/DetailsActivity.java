package com.rarcher.ovo.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rarcher.ovo.R;
import com.rarcher.ovo.Utils.tools.RxBus;
import com.rarcher.ovo.model.Details_static;
import com.rarcher.ovo.model.Event;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.image_iv)
    ImageView imageIv;
    @BindView(R.id.type_container)
    LinearLayout typeContainer;
    @BindView(R.id.comment_tv)
    TextView commentTv;
    @BindView(R.id.like_tv)
    TextView likeTv;
    @BindView(R.id.readcount_tv)
    TextView readcountTv;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.content_tv)
    TextView contentTv;
    @BindView(R.id.author_tv)
    TextView authorTv;
    @BindView(R.id.type_tv)
    TextView typeTv;
    @BindView(R.id.time_tv)
    TextView timeTv;
    @BindView(R.id.image_type)
    ImageView imageType;
    @BindView(R.id.download_start_white)
    ImageView downloadStartWhite;
    @BindView(R.id.home_advertise_iv)
    ImageView homeAdvertiseIv;
    @BindView(R.id.pager_content)
    RelativeLayout pagerContent;
    String TAG ="DetailsActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        Log.d(TAG, "onCreate: "+Details_static.getContext());
        Log.d(TAG, "onCreate: "+Details_static.getAuthor());
        Log.d(TAG, "onCreate: "+Details_static.getSourse());
        contentTv.setText(Details_static.getContext());
        Log.d(TAG, "onCreate: "+contentTv.getText().toString());
        authorTv.setText(Details_static.getAuthor());
        imageIv.setImageResource(Details_static.getSourse());

    }







}
