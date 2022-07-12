package com.example.smartapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class sendEmail extends AppCompatActivity {
    EditText et_to, et_subject, et_message;
    Button bt_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_email);

        et_to = findViewById(R.id.et_to);
        et_subject = findViewById(R.id.et_subject);
        et_message = findViewById(R.id.et_message);
        bt_send = findViewById(R.id.bt_send);

        bt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });
    }

    private void sendEmail() {
        String recipientList = et_to.getText().toString();
        String[] recipients = recipientList.split(",");

        String subject = et_subject.getText().toString();
        String message = et_message.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email client"));
    }
}
