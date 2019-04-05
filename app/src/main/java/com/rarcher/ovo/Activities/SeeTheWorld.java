package com.rarcher.ovo.Activities;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.rarcher.ovo.R;
import com.rarcher.ovo.View.ZoomView;

public class SeeTheWorld extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    String TAG="传感器";
    private Sensor sensor;
    ZoomView image;
    boolean first = true;
    private int firstx;
    private int firsty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_the_world);
        image = findViewById(R.id.views);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);        //获取Sensor实例
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);        //注册滚动事件
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);

    }
    /**
     * SensorEventListener接口 onSensorChanged方法     *
     */
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {




        //滚动
       float sensory = (float) (Math.round(sensorEvent.values[2] * 100)) / 100;
       //"方位角："
       float sensorx = (float) (Math.round(sensorEvent.values[0] * 100)) / 100;

       if (first){
           firstx=(int)sensorx;
           firsty=(int)sensory;
           image.beforechanging(first);
           first=false;
       }
       else {

           //TODO:需要调整乘上的倍数,然后调整一下y轴的移动越界问题,在面朝北方的时候,分界线为0到1k+,这个地方需要优化!!!!!!

           Log.d(TAG, "onSensorChanged: x的移动 "+(25*((int)sensory-firsty)));
           Log.d(TAG, "onSensorChanged: y的移动 "+(25*((int)sensorx-firstx)));
           image.setchanging((25*((int)sensory-firsty)),-(25*((int)sensorx-firstx)));
       }



    }

    /**
     * SensorEventListener接口 onAccuracyChanged方法     *
     */
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }
}
