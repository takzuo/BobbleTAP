package com.takzuo.bubbletap;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class StartUp extends AppCompatActivity {
    public static SharedPreferences preferences;

     @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.startup);
        }


    public void startgame1(View view) {
        preferences = getSharedPreferences( "level", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("speed", 15);
        editor.commit();

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void startgame2(View view) {
        preferences = getSharedPreferences( "level", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("speed", 25);
        editor.commit();

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void startgame3(View view) {
        preferences = getSharedPreferences( "level", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("speed", 45);
        editor.commit();

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }


}
