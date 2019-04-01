package com.rarcher.ovo.View.Adapter;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rarcher.ovo.R;
import com.rarcher.ovo.Utils.FingerprintUtil;
import com.rarcher.ovo.Utils.GlideApp;
import com.rarcher.ovo.Utils.ScreenUtils;
import com.rarcher.ovo.model.Item;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by 25532 on 2019/4/1.
 */

public class VerticalPagerAdapter extends RecyclerView.Adapter<VerticalPagerAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Item> mDatas;
    View view;
    AlertDialog dialog;
    ViewHolder holders;
    int positions;
    public VerticalPagerAdapter(Context mContext, ArrayList<Item> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.fragment_main, parent, false);
        view = LayoutInflater.from(mContext).inflate(R.layout.figure_dialog, null, false);
        dialog = new AlertDialog.Builder(mContext).setView(view).create();
        dialog.dismiss();
        FingerprintUtil.context = mContext;
        final ViewHolder holder = new ViewHolder(v);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               int position = holder.getAdapterPosition();
               Item item = mDatas.get(position);
               if (item.getId()=="123"){
                   Toast.makeText(mContext,"点击了文字",Toast.LENGTH_SHORT).show();
               }
               else if (item.getId()=="figure"&&item.isLock()){
                   onFingerprintClick(view);
               }
            }
        });


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holders = holder;
        positions = position;
        holder.pagerContent.setVisibility(View.VISIBLE);
        holder.homeAdvertiseIv.setVisibility(View.GONE);

        GlideApp.with(mContext).load(mDatas.get(position).getImageId()).centerCrop().into(holder.imageIv);
        holder.likeTv.setText(mDatas.get(position).getGood());
        holder.titleTv.setText(mDatas.get(position).getTitle());
        holder.readcountTv.setText(mDatas.get(position).getReadcount());
        holder.commentTv.setText(mDatas.get(position).getComment());
        Item item = mDatas.get(position);
        if (item.getId()=="figure"&&item.isLock()){
            holder.contentTv.setText("您必须先验证指纹才能查看时间胶囊的内容");
        }
        else holder.contentTv.setText(mDatas.get(position).getContext());

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



    public void onFingerprintClick(View v){

        FingerprintUtil.callFingerPrint(new FingerprintUtil.OnCallBackListenr() {

            @Override
            public void onSupportFailed() {
                Toast.makeText(mContext,"当前设备不支持指纹",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onInsecurity() {
                Toast.makeText(mContext,"当前设备未处于安全保护中",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onEnrollFailed() {

                Toast.makeText(mContext,"请到设置中设置指纹",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationStart() {
                showDialog();
            }

            @Override
            public void onAuthenticationError(int errMsgId, CharSequence errString) {

                Toast.makeText(mContext,errString.toString(),Toast.LENGTH_SHORT).show();
                showAuthenticationScreen();
                if (dialog != null  &&dialog.isShowing()){
                    dialog.dismiss();
                }
            }

            @Override
            public void onAuthenticationFailed() {
                Toast.makeText(mContext,"解锁失败",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
                Toast.makeText(mContext,helpString.toString(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
                Toast.makeText(mContext,"解锁成功",Toast.LENGTH_SHORT).show();
                if (dialog != null  &&dialog.isShowing()){
                    dialog.dismiss();
                }
                mDatas.get(positions).setLock(false);
                holders.contentTv.setText(mDatas.get(positions).getContext());

            }
        });
    }
    @TargetApi(23)

    /**

     * 锁屏密码

     */

    private void showAuthenticationScreen() {
    }
    //初始化并弹出对话框方法
    private void showDialog() {

        final Button cancel = view.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FingerprintUtil.cancel();
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.show();
        //此处设置位置窗体大小，我这里设置为了手机屏幕宽度的3/4  注意一定要在show方法调用后再写设置窗口大小的代码，否则不起效果会
        dialog.getWindow().setLayout((ScreenUtils.getScreenWidth(mContext) / 4 * 3), LinearLayout.LayoutParams.WRAP_CONTENT);
    }


}

