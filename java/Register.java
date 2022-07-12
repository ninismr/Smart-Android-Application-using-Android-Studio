package com.example.smartapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Register extends AppCompatActivity {
    Button register;
    EditText uname;
    EditText pswd;

    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.register_page);
        uname=(EditText)findViewById(R.id.et_username);
        pswd=(EditText)findViewById(R.id.et_password);
        register = findViewById(R.id.bt_register);
    }

    public void getRegister(View view) {
        String a=uname.getText().toString();
        String b=pswd.getText().toString();
        String s= "";
        try {
            URL url = new URL("https://jam2021.000webhostapp.com/JAM/register.php?a="+a+"&b="+b);
            URLConnection ucon = url.openConnection();
            InputStream in = ucon.getInputStream();
            InputStreamReader isw = new InputStreamReader(in);
            int data = isw.read();
            while (data != -1) {
                char current = (char) data;
                s=s+current;
                data = isw.read();

            }
            if(s.equals("You're Registered!")) {
                Intent login = new Intent(Register.this, Loginpage.class);
                startActivity(login);

            }
            else {
                register.setText(s);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void LoginPage(View view) {
        Intent login = new Intent(Register.this, Loginpage.class);
        startActivity(login);
    }
}
