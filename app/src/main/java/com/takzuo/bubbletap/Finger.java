package com.takzuo.bubbletap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

public class Finger {
    Context context;
    Bitmap ourfinger;
    int ox, oy;
    Random random;

    public Finger(Context context) {
        this.context = context;
        ourfinger = BitmapFactory.decodeResource(context.getResources(), R.drawable.hand);
        random = new Random();

    }


    public Bitmap getOurfinger(){
        return ourfinger;
    }


}