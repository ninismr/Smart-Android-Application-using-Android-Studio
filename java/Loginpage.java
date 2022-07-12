package com.example.smartapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Loginpage extends AppCompatActivity {
    Button login;
    EditText usernamedata;
    EditText passworddata;
    SharedPreferences sharedpreferences;
    public static String pref = "sPref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        usernamedata=(EditText)findViewById(R.id.et_username);
        passworddata=(EditText)findViewById(R.id.et_password);
        login = findViewById(R.id.bt_login);
    }

    public void getLogin(View view) {
        String username=usernamedata.getText().toString();
        String password=passworddata.getText().toString();
        String s= "";
        pref = "sPref";
        sharedpreferences = getSharedPreferences(pref, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("usernameS", username);
        editor.putString("passwordS", password);
        editor.commit();
        try {
            URL url = new URL("https://jam2021.000webhostapp.com/JAM/login.php?username="+username+"&password="+password);
            URLConnection ucon = url.openConnection();
            InputStream in = ucon.getInputStream();
            InputStreamReader isw = new InputStreamReader(in);
            int data = isw.read();
            while (data != -1) {
                char current = (char) data;
                s=s+current;
                data = isw.read();

            }
            if(s.equals("Login Successfully!")) {
                Intent startApp = new Intent(Loginpage.this, Frontpage.class);
                startActivity(startApp);

            }
            else {
                Toast.makeText(this, "Login Failed!", Toast.LENGTH_SHORT).show();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}

