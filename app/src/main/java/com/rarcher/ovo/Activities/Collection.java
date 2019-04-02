package com.rarcher.ovo.Activities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MenuItem;

import com.mcxtzhang.layoutmanager.swipecard.CardConfig;
import com.mcxtzhang.layoutmanager.swipecard.OverLayCardLayoutManager;
import com.mcxtzhang.layoutmanager.swipecard.RenRenCallback;
import com.rarcher.ovo.DB.LocalDB;
import com.rarcher.ovo.R;
import com.rarcher.ovo.View.Adapter.CollectionAdapter;
import com.rarcher.ovo.model.Collection_been;
import com.rarcher.ovo.model.History_Been;

import java.util.ArrayList;
import java.util.List;

public class Collection extends AppCompatActivity {

    private List<Collection_been> collection_beenList = new ArrayList<>();
    private CollectionAdapter adapter;
    private SwipeRefreshLayout swipeRefresh;
    private LocalDB localDB;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //重写ToolBar返回按钮的行为，防止重新打开父Activity重走生命周期方法
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        initDB();
        initDatas();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this );
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);

        adapter = new CollectionAdapter(collection_beenList);
        recyclerView.setAdapter(adapter);
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.collection_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFruits();
            }
        });
        

    }
    private void initDatas(){
        collection_beenList.clear();
        query(localDB,collection_beenList);
    }
    private void refreshFruits() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initDatas();
                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void initDB(){
        localDB = new LocalDB(getApplicationContext(),"Collection.db",null,2);
        localDB.getWritableDatabase();
    }
    public static void query(LocalDB dbhelper,List<Collection_been> list) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        Cursor cursor = db.query("Collection", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String context = cursor.getString(cursor.getColumnIndex("context"));
                String name = cursor.getString(cursor.getColumnIndex("author"));
                int imageid = cursor.getInt(cursor.getColumnIndex("imageId"));
                Collection_been collection_been = new Collection_been(context,name,imageid);
                list.add(collection_been);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
    }
}
