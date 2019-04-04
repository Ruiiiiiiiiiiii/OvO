package com.rarcher.ovo.View.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.rarcher.ovo.Activities.Collection;
import com.rarcher.ovo.Activities.DetailsActivity;
import com.rarcher.ovo.DB.LocalDB;
import com.rarcher.ovo.R;
import com.rarcher.ovo.model.Collection_been;
import com.rarcher.ovo.model.Details_static;

import java.util.List;

/**
 * Created by 25532 on 2019/4/2.
 */

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.ViewHolder> {
    private static final String TAG = "CollectionAdapter";
    private Context mContext;
    private List<Collection_been> collection_beenList;
    private LocalDB localDB;

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
        localDB = new LocalDB(view.getContext(), "Collection.db", null, 2);
        localDB.getWritableDatabase();
        final ViewHolder holder = new ViewHolder(view);
        holder.views.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Collection_been been = collection_beenList.get(position);
                Details_static.setAuthor(been.getAuthor());
                Details_static.setContext(been.getContext());
                Details_static.setSourse(been.getImageId());
                Intent intent = new Intent(v.getContext(), DetailsActivity.class);
                v.getContext().startActivity(intent);
            }
        });
        holder.views.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View view) {
                int position = holder.getAdapterPosition();
                final Collection_been been = collection_beenList.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("确定?");
                builder.setMessage("您确定要删除这个预约?");
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        delete(been.getContext());

                    }
                });
                builder.show();


                return true;
            }
        });
        return holder;
    }

    private void delete(String context) {
        SQLiteDatabase db = localDB.getWritableDatabase();
        db.delete("Collection", "context = ?", new String[]{context});
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
