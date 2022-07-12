package com.example.smartapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class TodoPage extends AppCompatActivity {
    List<String> todoList;
    ArrayAdapter<String> arrayAdapter;
    ListView listView;
    EditText etList;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        sharedpreferences = getSharedPreferences(Loginpage.pref, Context.MODE_PRIVATE);
        setContentView(R.layout.todo_page);

        todoList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this,R.layout.list_view,todoList);
        listView = findViewById(R.id.list_view);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView textView = (TextView) view;
                textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }
        });

        etList = findViewById(R.id.et_list);
    }

    public void addList(View view){
        String sndusername = sharedpreferences.getString("usernameS", null);
        String sndpassword = sharedpreferences.getString("passwordS", null);
        String todobox = etList.getText().toString();
        todoList.add(etList.getText().toString());
        arrayAdapter.notifyDataSetChanged();
        String s = "";
        try {
            URL url = new URL("https://jam2021.000webhostapp.com/JAM/tododata.php?sndusername="+sndusername+"&sndpassword="+sndpassword+"&todobox="+todobox);
            URLConnection ucon = url.openConnection();
            InputStream in = ucon.getInputStream();
            InputStreamReader isw = new InputStreamReader(in);
            int data = isw.read();
            while (data != -1) {
                char current = (char) data;
                s=s+current;
                data = isw.read();
            }
            etList.setText("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}