package com.rarcher.ovo.Activities;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rarcher.ovo.R;
import com.rarcher.ovo.View.Magnifier;

public class SeeTheWorld extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor sensor;
    Magnifier image;

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

        image.setchange((int)sensorx,(int)sensory);
    }

    /**
     * SensorEventListener接口 onAccuracyChanged方法     *
     */
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }
}
