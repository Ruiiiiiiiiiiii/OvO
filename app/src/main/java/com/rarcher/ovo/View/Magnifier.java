package com.rarcher.ovo.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.rarcher.ovo.R;

import static android.content.ContentValues.TAG;

/**
 * Created by 25532 on 2019/4/4.
 */
public class Magnifier extends View {

    private final Path mPath = new Path();
    private final Matrix matrix = new Matrix();
    private DisplayMetrics dm;
    private int mScreenWidth, mScreenHeight;
    private static Bitmap bitmap;
    // 放大镜的半径
    private static final int RADIUS = 160;
    // 放大倍数
    private static final float FACTOR = 5.0f;
    private static int mCurrentX=200;
    private static int mCurrentY=100;

    private static int lasty=0;
    private static int lastx=0;



    public Magnifier(Context context) {
        this(context, null);
    }

    public Magnifier(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPath.addCircle(RADIUS,RADIUS, 0, Path.Direction.CW);
        matrix.setScale(FACTOR, FACTOR);

        dm = getResources().getDisplayMetrics();
        mScreenWidth = dm.widthPixels;
        mScreenHeight = dm.heightPixels;
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.star2);
        bitmap = upImageSize(bitmap);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mCurrentX = (int) event.getX();
        mCurrentY = (int) event.getY();
        if (mCurrentX+RADIUS > bitmap.getWidth()) {
            mCurrentX = bitmap.getWidth()-RADIUS;
        } else if (mCurrentX-RADIUS <0 ) {
            mCurrentX = RADIUS;
        }
        if (mCurrentY+RADIUS > bitmap.getHeight()) {
            mCurrentY = bitmap.getHeight()-RADIUS;
        } else if (mCurrentY-RADIUS <0 ) {
            mCurrentY = RADIUS;
        }
        /*if (mCurrentX > bitmap.getWidth()) {
            mCurrentX = bitmap.getWidth();
        } else if (mCurrentX <0 ) {
            mCurrentX = 0;
        }
        if (mCurrentY > bitmap.getHeight()) {
            mCurrentY = bitmap.getHeight();
        } else if (mCurrentY <0 ) {
            mCurrentY = 0;
        }*/
        invalidate();
        return true;
    }


    public void setchange(int x, int y){
        //TODO:灵敏度太高了
        mCurrentX += lastx+x;
        mCurrentY += lasty+y;
        lastx +=lastx+x;
        lasty +=lasty+y;
        Log.d(TAG, "setchange: lastx"+lastx);
        Log.d(TAG, "setchange: lasty"+lasty);
        if (mCurrentX+RADIUS > bitmap.getWidth()) {
            mCurrentX = bitmap.getWidth()-RADIUS;
        } else if (mCurrentX-RADIUS <0 ) {
            mCurrentX = RADIUS;
        }
        if (mCurrentY+RADIUS > bitmap.getHeight()) {
            mCurrentY = bitmap.getHeight()-RADIUS;
        } else if (mCurrentY-RADIUS <0 ) {
            mCurrentY = RADIUS;
        }
        invalidate();


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
       // mPath.addCircle(RADIUS, RADIUS, RADIUS, Path.Direction.CW);
        mPath.addRect(-5, 200, 1000, 900,Path.Direction.CCW);
        // 底图
        //canvas.drawBitmap(bitmap, 0, 0, null);
        // 剪切
        canvas.translate(RADIUS, RADIUS);
        canvas.clipPath(mPath);
        // 画放大后的图
        canvas.translate(RADIUS - mCurrentX * FACTOR, RADIUS - mCurrentY* FACTOR);
        canvas.drawBitmap(bitmap, matrix, null);
    }

    //等比缩放图片资源
    public Bitmap upImageSize(Bitmap bmp) {
        if (bmp == null) {
            return null;
        }
        // 计算比例
        float scaleX = (float) mScreenWidth / bmp.getWidth();// 宽的比例
        float scaleY = (float) mScreenHeight / bmp.getHeight();// 高的比例
        //新的宽高
        int newW = 0;
        int newH = 0;
        if (scaleX < scaleY) {
            newW = (int) (bmp.getWidth() * scaleX);
            newH = (int) (bmp.getHeight() * scaleX);
        } else if (scaleX >= scaleY) {
            newW = (int) (bmp.getWidth() * scaleY);
            newH = (int) (bmp.getHeight() * scaleY);
        }
        Log.i("Bitmap rate:", scaleX + "," + scaleY);
        return Bitmap.createScaledBitmap(bmp, newW, newH, true);
    }
}
