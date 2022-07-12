package com.example.smartapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class chatApp extends AppCompatActivity {
    TextView hdrdata;
    TextView threadcnt;
    TextView chattext;
    EditText chatboxdata;
    SharedPreferences sharedpreferences;
    Boolean t;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        sharedpreferences = getSharedPreferences(Loginpage.pref, Context.MODE_PRIVATE);
        setContentView(R.layout.chatlayout);
        hdrdata = (TextView) findViewById(R.id.hdr);
        threadcnt = (TextView) findViewById(R.id.thread);
        chattext = (TextView) findViewById(R.id.chatxt);
        chatboxdata = (EditText) findViewById(R.id.chatbox);
        String sndusername = sharedpreferences.getString("usernameS", null);
        String sndpassword = sharedpreferences.getString("passwordS", null);
        String hdr = hdrdata.getText().toString();
        String s = "";
        try {
            URL url = new URL("https://jam2021.000webhostapp.com/JAM/chatonload.php?sndusername="+sndusername+"&sndpassword="+sndpassword+"&hdr="+hdr);
            URLConnection ucon = url.openConnection();
            InputStream in = ucon.getInputStream();
            InputStreamReader isw = new InputStreamReader(in);
            int data = isw.read();
            while (data != -1) {
                char current = (char) data;
                s=s+current;
                data = isw.read();
            }
            chattext.setText(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void tmr() {
        t=true;
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (t) {
                    try {
                        i++;
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            threadcnt.setText("" + i +"s");
                        }
                    });
                }
            }
        };
        new Thread(runnable).start();
    }

        public void thr(View v) {
            tmr();
        }

        public void send(View view) {
            String sndusername = sharedpreferences.getString("usernameS", null);
            String sndpassword = sharedpreferences.getString("passwordS", null);
            String chatbox = chatboxdata.getText().toString();
            String hdr = hdrdata.getText().toString();
            String s = "";
            try {
                URL url = new URL("https://jam2021.000webhostapp.com/JAM/sndchat.php?sndusername="+sndusername+"&sndpassword="+sndpassword+"&chatbox="+chatbox+"&hdr="+hdr);
                URLConnection ucon = url.openConnection();
                InputStream in = ucon.getInputStream();
                InputStreamReader isw = new InputStreamReader(in);
                int data = isw.read();
                while (data != -1) {
                    char current = (char) data;
                    s=s+current;
                    data = isw.read();
                }
                chattext.setText(s);
                chatboxdata.setText("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
