package com.rarcher.ovo.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.rarcher.ovo.R;
import com.rarcher.ovo.model.Collection_been;

import java.util.List;

/**
 * Created by 25532 on 2019/4/2.
 */

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.ViewHolder>  {
    private static final String TAG = "CollectionAdapter";
    private Context mContext;
    private List<Collection_been> collection_beenList;
    static class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout views;
        ImageView Image;
        TextView contexts;
        TextView author;
        public ViewHolder(View view) {
            super(view);
            views = view.findViewById(R.id.type_container);
            Image = (ImageView) view.findViewById(R.id.image_iv);
            contexts = (TextView) view.findViewById(R.id.title_tv);
            author = view.findViewById(R.id.author_tv);
        }
    }
    public CollectionAdapter(List<Collection_been> list) {
        collection_beenList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_art, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.views.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Collection_been collection = collection_beenList.get(position);
        holder.contexts.setText(collection.getContext());
        holder.author.setText(collection.getAuthor());
        Glide.with(mContext).load(collection.getImageId()).into(holder.Image);
    }

    @Override
    public int getItemCount() {
        return collection_beenList.size();
    }
}
