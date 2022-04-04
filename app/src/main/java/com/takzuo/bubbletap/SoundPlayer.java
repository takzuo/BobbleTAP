package com.takzuo.bubbletap;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class SoundPlayer {
    private static SoundPool soundPool;
    private static int bubblesSound;
    private static int bubblingSound;


    public  SoundPlayer(Context context){
        //SoundPool(int maxStreams, int streamType, int srcQuality)

        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);

        bubblesSound = soundPool.load(context, R.raw.bubbles, 1);
        bubblingSound = soundPool.load(context, R.raw.bubbling, 1);

    }

    public void playBubblesSound(){
        //play(int soundID, float leftVolume, float rightVolume, int priority, int loop, float rate)

        soundPool.play(bubblesSound, 1.0f, 1.0f,1, 0, 1.0f);
    }

    public  void playBubblingSound(){

        soundPool.play(bubblingSound, 1.0f, 1.0f,1, 0, 1.0f);

    }

}
