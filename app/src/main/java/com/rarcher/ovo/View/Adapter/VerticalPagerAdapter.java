package com.rarcher.ovo.View.Adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rarcher.ovo.R;
import com.rarcher.ovo.Utils.GlideApp;
import com.rarcher.ovo.model.Item;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by 25532 on 2019/4/1.
 */

public class VerticalPagerAdapter extends RecyclerView.Adapter<VerticalPagerAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Item> mDatas;

    public VerticalPagerAdapter(Context mContext, ArrayList<Item> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.fragment_main, parent, false);
        final ViewHolder holder = new ViewHolder(v);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               int position = holder.getAdapterPosition();
               Item item = mDatas.get(position);
               if (item.getId()=="123"){
                   Toast.makeText(mContext,"点击了文字",Toast.LENGTH_SHORT).show();
               }
            }
        });


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.pagerContent.setVisibility(View.VISIBLE);
        holder.homeAdvertiseIv.setVisibility(View.GONE);

        GlideApp.with(mContext).load(mDatas.get(position).getImageId()).centerCrop().into(holder.imageIv);
        holder.likeTv.setText(mDatas.get(position).getGood());
        holder.titleTv.setText(mDatas.get(position).getTitle());
        holder.contentTv.setText(mDatas.get(position).getContext());
        holder.readcountTv.setText(mDatas.get(position).getReadcount());
        holder.commentTv.setText(mDatas.get(position).getComment());
        holder.typeTv.setText(mDatas.get(position).getType());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
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

        View view;

        public ViewHolder(View itemView) {
            super(itemView);
            imageIv = itemView.findViewById(R.id.image_iv);
            typeContainer = itemView.findViewById(R.id.type_container);
            commentTv = itemView.findViewById(R.id.comment_tv);
            likeTv = itemView.findViewById(R.id.like_tv);
            readcountTv = itemView.findViewById(R.id.readcount_tv);
            titleTv = itemView.findViewById(R.id.title_tv);
            contentTv = itemView.findViewById(R.id.content_tv);
            pagerContent = itemView.findViewById(R.id.pager_content);
            homeAdvertiseIv = itemView.findViewById(R.id.home_advertise_iv);
            typeTv = itemView.findViewById(R.id.type_tv);
            view = itemView;

        }
    }
}

