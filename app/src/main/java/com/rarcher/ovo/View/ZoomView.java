package com.rarcher.ovo.View;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.rarcher.ovo.R;

import static android.content.ContentValues.TAG;


/**
 * Created by 25532 on 2019/4/5.
 */


public class ZoomView extends android.support.v7.widget.AppCompatImageView {


    //移动模式和放大模式(测试用的)
    final public static int DRAG = 1;
    final public static int ZOOM = 2;
    public int mode = 0;
    //三个矩阵
    private Matrix matrix = new Matrix();
    private Matrix matrix1 = new Matrix();
    private Matrix saveMatrix = new Matrix();
    //测试用的手指触摸
    private float x_down = 0;
    private float y_down = 0;
    //这个是设置的图片
    private Bitmap touchImg;
    private PointF mid = new PointF();

    private int screenWidth, screenHeight;
    private float[] x = new float[4];
    private float[] y = new float[4];
    private boolean flag = false;

    //设置初始时候的放大图片位置
    //TODO:需要修改一下,调整到图片中央附近,注意一定是负数!!!!!千万不能写正数
    private final int startx=-500;
    private final int starty=-500;



    public ZoomView(Context context) {
        super(context);
        touchImg = BitmapFactory.decodeResource(getResources(), R.drawable.star2);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
        matrix = new Matrix();
        // 计算出缩放比例
        float scale = 5.0f;
        // 以mid为中心进行缩放
        matrix1.postScale(scale, scale, mid.x, mid.y);
        flag = checkMatrix(matrix1);
        if (flag) {
            matrix.set(matrix1);
            invalidate();
        }

        saveMatrix.set(matrix);
        // 平移 当前坐标减去初始坐标 移动的距离
        matrix1.postTranslate(startx,starty);
        // 平移
        // 判断达到移动标准
        // 设置matrix
        matrix.set(matrix1);
        // 调用ondraw重绘
        invalidate();



    }

    public ZoomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        touchImg = BitmapFactory.decodeResource(getResources(), R.drawable.star2);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
        matrix = new Matrix();
        // 计算出缩放比例
        float scale = 5.0f;
        // 以mid为中心进行缩放
        matrix1.postScale(scale, scale, mid.x, mid.y);
        flag = checkMatrix(matrix1);
        if (flag) {
            matrix.set(matrix1);
            invalidate();
        }

        saveMatrix.set(matrix);
        // 平移 当前坐标减去初始坐标 移动的距离
        matrix1.postTranslate(startx,starty);
        // 平移
        // 判断达到移动标准
            // 设置matrix
            matrix.set(matrix1);
            // 调用ondraw重绘
            invalidate();




    }

    public ZoomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        touchImg = BitmapFactory.decodeResource(getResources(), R.drawable.star2);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
        matrix = new Matrix();
        matrix1.set(saveMatrix);
        // 计算出缩放比例
        float scale = 5.0f;
        // 以mid为中心进行缩放
        matrix1.postScale(scale, scale, mid.x, mid.y);
        flag = checkMatrix(matrix1);
        if (flag) {
            matrix.set(matrix1);
            invalidate();
        }

        saveMatrix.set(matrix);
        // 平移 当前坐标减去初始坐标 移动的距离
        matrix1.postTranslate(startx,starty);
        // 平移
        // 判断达到移动标准
        // 设置matrix
        matrix.set(matrix1);
        // 调用ondraw重绘
        invalidate();



    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();        // 根据 matrix 来重绘新的view
        canvas.drawBitmap(touchImg, matrix, null);
        canvas.restore();
    }

    public void beforechanging(boolean first){
        if (first)
        saveMatrix.set(matrix);
        else ;
    }

    //从传感器方向获取数据Δx和Δy,调整当前的位置点
    public void setchanging(int dx,int dy){
        // 设置当前的 matrix
        matrix1.set(saveMatrix);
        // 平移 当前坐标减去初始坐标 移动的距离
        matrix1.postTranslate(dx,dy);
        //Log.d(TAG, "onTouchEvent: ++++++++++++++++++++++++++++++eventx"+event.getX());
        //Log.d(TAG, "onTouchEvent: ++++++++++++++++++++++++++++++eventy"+event.getY());
        // 平移
        // 判断达到移动标准

            // 设置matrix
            matrix.set(matrix1);
            // 调用ondraw重绘
            invalidate();

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();        // 多点触摸的时候 必须加上MotionEvent.ACTION_MASK
        switch (action & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                saveMatrix.set(matrix);
                x_down = event.getX();
                y_down = event.getY();
                // 初始为drag模式
                mode = DRAG;
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                saveMatrix.set(matrix);
                // 初始的两个触摸点间的距离
                // 设置为缩放模式
                mode = ZOOM;
                // 多点触摸的时候 计算出中间点的坐标
                midPoint(mid, event);
                break;
            case MotionEvent.ACTION_MOVE:
                // drag模式
                if (mode == DRAG) {
                    // 设置当前的 matrix
                    matrix1.set(saveMatrix);
                    // 平移 当前坐标减去初始坐标 移动的距离
                    matrix1.postTranslate(event.getX() - x_down, event.getY() - y_down);
                    //Log.d(TAG, "onTouchEvent: ++++++++++++++++++++++++++++++eventx"+event.getX());
                    //Log.d(TAG, "onTouchEvent: ++++++++++++++++++++++++++++++eventy"+event.getY());
                    // 平移
                    // 判断达到移动标准
                    flag = checkMatrix(matrix1);
                    if (flag) {
                        // 设置matrix
                        matrix.set(matrix1);
                        // 调用ondraw重绘
                        invalidate();
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                mode = 0;
                break;
        }
        return true;
    }


    //取两点的中点
    private void midPoint(PointF point, MotionEvent event) {
        try {
            float x = event.getX(0) + event.getX(1);
            float y = event.getY(0) + event.getY(1);
            point.set(x / 2, y / 2);
        } catch (IllegalArgumentException ex) {
            Log.v("TAG", ex.getLocalizedMessage());
        }
    }

    //没什么意义,调整好了Δx和Δy就不需要调整边界了
    private boolean checkMatrix(Matrix m) {
        GetFour(m);
        // 出界判断
        // view的右边缘x坐标小于屏幕宽度的1/3的时候，
        // view左边缘大于屏幕款短的2/3的时候
        // view的下边缘在屏幕1/3上的时候
        // view的上边缘在屏幕2/3下的时候
//        if (       (x[0] < screenWidth  && x[1] < screenWidth && x[2] < screenWidth  && x[3] < screenWidth )
//                || (x[0] > screenWidth && x[1] > screenWidth  && x[2] > screenWidth  && x[3] > screenWidth )
//                || (y[0] < screenHeight  && y[1] < screenHeight  && y[2] < screenHeight && y[3] < screenHeight )
//                || (y[0] > screenHeight  && y[1] > screenHeight && y[2] > screenHeight  && y[3] > screenHeight )) {
//            return true;
//        }
        // 图片现宽度

        double width = Math.sqrt((x[0] - x[1]) * (x[0] - x[1]) + (y[0] - y[1]) * (y[0] - y[1]));
        Log.d(TAG, "checkMatrix: width"+screenWidth);
        Log.d(TAG, "checkMatrix: hight"+screenHeight);
        Log.d(TAG, "checkMatrix: "+width);
        // 缩放比率判断 宽度打雨3倍屏宽，或者小于1/3屏宽
        if (width/2 < screenWidth || width/2 > screenWidth ) {
            return true;
        }

//         if ((x[0] >= 0 && x[1] >= 0 && x[2] >= 0 && x[3] >= 0)
//         && (x[0] <= screenWidth && x[1] <= screenWidth
//         && x[2] <= screenWidth && x[3] <= screenWidth)
//         && (y[0] >= 0 && y[1] >= 0 && y[2] >= 0 && y[3] >= 0) && (y[0] <=
//         screenHeight
//         && y[1] <= screenHeight && y[2] <= screenHeight && y[3] <=
//         screenHeight)) {
//
//         return true;
//         }

         return false;/*
       /* Log.d(TAG, "checkMatrix: width"+screenWidth);
        Log.d(TAG, "checkMatrix: hight"+screenHeight);

        Log.d(TAG, "checkMatrix: x[0]"+x[0]);
        Log.d(TAG, "checkMatrix: y[0]"+y[0]);

        Log.d(TAG, "checkMatrix: x[1]"+x[1]);
        Log.d(TAG, "checkMatrix: y[1]"+y[1]);

        Log.d(TAG, "checkMatrix: x[2]"+x[2]);
        Log.d(TAG, "checkMatrix: y[2]"+y[2]);

        Log.d(TAG, "checkMatrix: x[3]"+x[3]);
        Log.d(TAG, "checkMatrix: y[3]"+y[3]);
        Log.d(TAG, "checkMatrix:                                     ");
        Log.d(TAG, "checkMatrix:                                     ");
        if (x[0]>=0&&x[1]<=touchImg.getWidth()*5&&y[0]>=0&&y[1]>=0&&y[2]<=touchImg.getHeight()*5&&y[3]<=touchImg.getHeight()*5&&x[2]>=0&&x[3]<=touchImg.getWidth()*5){
            return true;
        }
        else return false;
*/



    }

    //不管这个方法,废弃!!!
    private void GetFour(Matrix matrix) {
        float[] f = new float[9];
        matrix.getValues(f);
        //		StringBuffer sb = new StringBuffer();
        // 		for(float ff : f)
        // 		{
        // 			sb.append(ff+"  ");
        // 		}
        // 图片4个顶点的坐标
        // 矩阵  9     MSCALE_X 缩放的， MSKEW_X 倾斜的    。MTRANS_X 平移的
        x[0] = f[Matrix.MSCALE_X] * 0 + f[Matrix.MSKEW_X] * 0 + f[Matrix.MTRANS_X];
        y[0] = f[Matrix.MSKEW_Y] * 0 + f[Matrix.MSCALE_Y] * 0 + f[Matrix.MTRANS_Y];
        x[1] = f[Matrix.MSCALE_X] * touchImg.getWidth() + f[Matrix.MSKEW_X] * 0 + f[Matrix.MTRANS_X];
        y[1] = f[Matrix.MSKEW_Y] * touchImg.getWidth() + f[Matrix.MSCALE_Y] * 0 + f[Matrix.MTRANS_Y];
        x[2] = f[Matrix.MSCALE_X] * 0 + f[Matrix.MSKEW_X] * touchImg.getHeight() + f[Matrix.MTRANS_X];
        y[2] = f[Matrix.MSKEW_Y] * 0 + f[Matrix.MSCALE_Y] * touchImg.getHeight() + f[Matrix.MTRANS_Y];
        x[3] = f[Matrix.MSCALE_X] * touchImg.getWidth() + f[Matrix.MSKEW_X] * touchImg.getHeight() + f[Matrix.MTRANS_X];
        y[3] = f[Matrix.MSKEW_Y] * touchImg.getWidth() + f[Matrix.MSCALE_Y] * touchImg.getHeight() + f[Matrix.MTRANS_Y];
    }

}