package com.takzuo.bubbletap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

public class Canyon {
    Context context;
    Bitmap canyonbobble;
    int ex, ey;
    int canyonvelocity;
    Random random;

    public Canyon(Context context) {
        this.context = context;
        canyonbobble = BitmapFactory.decodeResource(context.getResources(), R.drawable.canionair1);
        random = new Random();
        ex = 0;
        ey = 0;
        canyonvelocity = 20;
    }

    public Bitmap getCanyonbobble(){
        return canyonbobble;
    }

    int getCanyonWidth(){
        return canyonbobble.getWidth();
    }
}