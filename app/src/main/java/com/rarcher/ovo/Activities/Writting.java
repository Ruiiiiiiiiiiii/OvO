package com.rarcher.ovo.Activities;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.rarcher.ovo.DB.LocalDB;
import com.rarcher.ovo.R;

public class Writting extends AppCompatActivity {
    FloatingActionButton fab;
    String context;
    EditText inputs;
    private LocalDB localDB;
    Calendar c=Calendar.getInstance();


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
        setContentView(R.layout.activity_writting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbar.setTitle("平行世界");
        init();
        initDB();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context = inputs.getText().toString();
                LocalDB.insert_info(context,c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DATE),"时间旅行者",localDB);
                Toast.makeText(getApplicationContext(),"时间旅行者已经上路啦!",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
    private void initDB(){
        localDB = new LocalDB(getApplicationContext(),"History.db",null,2);
        localDB.getWritableDatabase();
    }

    private void init(){
        fab= (FloatingActionButton) findViewById(R.id.fab);
        inputs = findViewById(R.id.inputs);
    }

}
