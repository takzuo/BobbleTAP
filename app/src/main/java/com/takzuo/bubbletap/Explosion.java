package com.takzuo.bubbletap;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class Explosion {

    Bitmap explosion[] = new Bitmap[9];
    int explosionFrame;
    int eX, eY;

    public  Explosion(Context context, int eX, int eY){
        explosion[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.explote1);
        explosion[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.explote2);
        explosion[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.explote3);
        explosion[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.explote4);
        explosion[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.explote5);
        explosion[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.explote6);
        explosion[6] = BitmapFactory.decodeResource(context.getResources(), R.drawable.explote7);
        explosion[7] = BitmapFactory.decodeResource(context.getResources(), R.drawable.explote8);

        explosionFrame = 0;
        this.eX = eX;
        this.eY = eY;

    }

    public Bitmap getExplosion(int explosionFrame){
        return explosion[explosionFrame];
    }



}
