package com.example.smartapp;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.io.File;

public class Frontpage extends AppCompatActivity {
    TextView textView;
    CardView msg, todo, msc,
    vid, mail, loc;
    DownloadManager dm;
    MediaPlayer mp;
    long dl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.front_page);
        textView = (TextView) findViewById(R.id.title_view);
        msg = (CardView) findViewById(R.id.msg);
        todo = (CardView) findViewById(R.id.todo);
        msc = (CardView) findViewById(R.id.msc);
        vid = (CardView) findViewById(R.id.vid);
        mail = (CardView) findViewById(R.id.mail);
        loc = (CardView) findViewById(R.id.loc);

        mp = MediaPlayer.create(Frontpage.this, Uri.parse(getExternalFilesDir(Environment.
                DIRECTORY_DOWNLOADS) + "/Paradise.mp3"));
    }

    public void getChat(View view) {
        Intent strChat = new Intent(Frontpage.this, chatApp.class);
        startActivity(strChat);
    }

    public void getTodo(View view) {
        Intent strTodo = new Intent(Frontpage.this, TodoPage.class);
        startActivity(strTodo);
    }

    public void getMusic(View view) {
        dm = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse("https://jam2021.000webhostapp.com/JAM/Paradise.mp3");
        DownloadManager.Request r = new DownloadManager.Request(uri);

        r.setDestinationUri(Uri.fromFile(new File(Frontpage.this.getExternalFilesDir(Environment
                .DIRECTORY_DOWNLOADS), "/Paradise.mp3")));
        r.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        dl = dm.enqueue(r);
        Toast.makeText(getBaseContext(), "Downloading in Progress!", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getBaseContext(), "Download Successful!", Toast.LENGTH_LONG).show();
                mp.start();
            }
        }, 3000);
    }

    public void stopMusic(View view) {
        if (mp.isPlaying()) {
            mp.pause();
        } else {
            mp.start();
        }
    }

    public void getVid(View view) {
        Intent strVid= new Intent(Frontpage.this, getVideo.class);
        startActivity(strVid);
    }

    public void getMap(View view) {
        Intent getMap= new Intent(Frontpage.this, getMap.class);
        startActivity(getMap);
    }

    public void sendEmail(View view) {
        Intent sendEmail= new Intent(Frontpage.this, sendEmail.class);
        startActivity(sendEmail);
    }
}
