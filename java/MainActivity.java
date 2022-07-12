package com.example.smartapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.media.MediaPlayer;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mc = MediaPlayer.create(MainActivity.this, Uri.parse(getExternalFilesDir(Environment.
                DIRECTORY_DOWNLOADS) + "/Paradise.mp3"));
    }

    public void register(View view) {
        Intent signup = new Intent(MainActivity.this, Register.class);
        startActivity(signup);
    }

    public void music(View v) {
        Uri uri = Uri.parse("https://jam2021.000webhostapp.com/JAM/Paradise.mp3");
        if (mc.isPlaying()) {
            mc.pause();
        } else {
            mc.start();
        }
    }
}