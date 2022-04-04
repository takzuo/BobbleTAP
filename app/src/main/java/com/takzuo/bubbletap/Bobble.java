package com.takzuo.bubbletap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Bobble {
    Bitmap booble;
    Context context;
    int shx, shy;

    public Bobble(Context context, int shx, int shy) {
        this.context = context;
        booble = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.bobble);
        this.shx = shx;
        this.shy = shy;
    }
    public Bitmap getBooble(){
        return booble;
    }


}