package com.takzuo.bubbletap;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class Gramawall {

    Context context;
    Bitmap gramawall;


    public Gramawall(Context context){
        this.context = context;
        gramawall = BitmapFactory.decodeResource(context.getResources(), R.drawable.gramaimage);
       }


    public Bitmap getGrama(){
        return gramawall;
    }


    int getGramaHeight(){
        return gramawall.getHeight();

    }

}
