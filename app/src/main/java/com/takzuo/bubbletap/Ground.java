package com.takzuo.bubbletap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import java.util.Random;


public class Ground extends View {

    int velocity = StartUp.preferences.getInt("speed", 20 );
    private SoundPlayer sound;

    Context context;
    Bitmap meduimlevel, life1, life2, life3, life4, life5;
    Handler handler;
    long UPDATE_MILLIS = 60;
    static int screenWidth, screenHeight;
    int points = 0;
    int life = 4;
    int bobblevelocity = velocity;
    Paint scorePaint;
    int TEXT_SIZE = 80;
    boolean paused = false;
    Gramawall gramawall;
    Finger finger;
    Canyon canyon;

    Random random;
    ArrayList<Bobble> enemyBobbles;
    Explosion explosion;
    ArrayList<Explosion> explosions;
    boolean enemyBobbleAction = false;

    final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            invalidate();
        }
    };


    public Ground(Context context) {
        super(context);
        this.context = context;
        Display display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
        random = new Random();
        enemyBobbles = new ArrayList<>();
        explosions = new ArrayList<>();
        finger = new Finger(context);
        gramawall = new Gramawall(context);
        canyon = new Canyon(context);
        handler = new Handler();
        sound = new SoundPlayer(context);
        meduimlevel = BitmapFactory.decodeResource(context.getResources(), R.drawable.finaleasy, null);
        life1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.life1, null);
        life2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.life2, null);
        life3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.life3, null);
        life4 = BitmapFactory.decodeResource(context.getResources(), R.drawable.life4, null);
        life5 = BitmapFactory.decodeResource(context.getResources(), R.drawable.life5, null);
        scorePaint = new Paint();
        scorePaint.setColor(Color.RED);
        scorePaint.setTextSize(TEXT_SIZE);
        scorePaint.setTextAlign(Paint.Align.LEFT);

        //System.out.println(str);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawBitmap(meduimlevel, 0, 0, null);
        canvas.drawText("Puntos : " + points, 0, TEXT_SIZE, scorePaint);

        // Draw life
        switch (life){
            case 4:
                canvas.drawBitmap(life1, screenWidth - life1.getWidth()  , 0, null);
            break;
            case 3:
                canvas.drawBitmap(life2, screenWidth - life2.getWidth()  , 0, null);
                break;
            case 2:
                canvas.drawBitmap(life3, screenWidth - life3.getWidth(), 0, null);
                break;
            case 1:
                canvas.drawBitmap(life4, screenWidth - life4.getWidth(), 0, null);
                break;
            default:
                canvas.drawBitmap(life5, screenWidth - life5.getWidth(), 0, null);
                break;
        }

        // When life 0, launch GameOver Activity with points
        if(life == 0){
            paused = true;
            handler = null;
            Intent intent = new Intent(context, GameOver.class);
            intent.putExtra("points", points);
            context.startActivity(intent);
            ((Activity) context).finish();
        }
        // Move canyon
        canyon.ex += canyon.canyonvelocity;
        // If canyon collides with right wall, reverse canyonVelocity TODO (+ & -)?
        if(canyon.ex + canyon.getCanyonWidth() >= screenWidth - 30){
            canyon.canyonvelocity *= -1;
        }
        // If enemySpaceship collides with left wall, again reverse enemyVelocity
        if(canyon.ex <=0){
            canyon.canyonvelocity *= -1;
        }


        // Draw the bobble canyon
        if(enemyBobbleAction == false){

            if(canyon.ex >= 100 + random.nextInt(800)){
                Bobble enemyBobble = new Bobble(context, canyon.ex + canyon.getCanyonWidth() / 2, canyon.ey );
                enemyBobbles.add(enemyBobble);
                enemyBobbleAction = true;
            }
            else{
                Bobble enemyBobble = new Bobble(context, canyon.ex + canyon.getCanyonWidth() / 2, canyon.ey );
                enemyBobbles.add(enemyBobble);
                enemyBobbleAction = true;
            }
        }


        canvas.drawBitmap(canyon.getCanyonbobble(), canyon.ex, canyon.ey, null);

        //Draw grama
        int lax = screenHeight - (gramawall.getGramaHeight() /2)   ;

        //int lax = 600;
        canvas.drawBitmap(gramawall.getGrama(), 2, lax, null);

        // Draw finger
        canvas.drawBitmap(finger.getOurfinger(), finger.ox, finger.oy, null);

        // Draw the canyon shot bobbles
        for(int i = 0; i < enemyBobbles.size(); i++){
            enemyBobbles.get(i).shy += bobblevelocity;
            canvas.drawBitmap(enemyBobbles.get(i).getBooble(), enemyBobbles.get(i).shx, enemyBobbles.get(i).shy, null);

            if((enemyBobbles.get(i).shy + 50 > lax )){

                life--;
                explosion = new Explosion(context, enemyBobbles.get(i).shx, enemyBobbles.get(i).shy);
                enemyBobbles.remove(i);
                explosions.add(explosion);

            }else if((enemyBobbles.get(i).shx >= finger.ox - 40)
                    &&enemyBobbles.get(i).shx <= finger.ox + 40
                    && enemyBobbles.get(i).shy >= finger.oy - 40
                    && enemyBobbles.get(i).shy <= finger.oy + 40
            ){
                enemyBobbles.remove(i);
                explosion = new Explosion(context, finger.ox, finger.oy);
                    if(canyon.canyonvelocity > 0){
                       canyon.canyonvelocity += 5;
                    }else{
                       canyon.canyonvelocity -= 5;
                    }

                bobblevelocity+=2;
                points++;
                explosions.add(explosion);
            }else if(enemyBobbles.get(i).shy >= screenHeight){
                enemyBobbles.remove(i);
            }
            if(enemyBobbles.size() < 1){
                enemyBobbleAction = false;
            }
        }

        // Explosion
        for(int i=0; i < explosions.size(); i++){
            canvas.drawBitmap(explosions.get(i).getExplosion(explosions.get(i).explosionFrame), explosions.get(i).eX, explosions.get(i).eY, null);
            sound.playBubblingSound();
            explosions.get(i).explosionFrame++;
            if(explosions.get(i).explosionFrame > 7){
                explosions.remove(i);
            }
        }


        // UPDATE_MILLIS.
        if(!paused)
            handler.postDelayed(runnable, UPDATE_MILLIS);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int touchX = (int)event.getX();
        int touchY = (int)event.getY();

        // When event.getAction() is MotionEvent.ACTION_DOWN, control finger
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            finger.ox = touchX;
            finger.oy = touchY;
        }
        // When event.getAction() is MotionEvent.ACTION_MOVE, control finger along touch
        if(event.getAction() == MotionEvent.ACTION_MOVE){
            finger.ox = touchX;
            finger.oy = touchY;
        }

        return true;
    }
}