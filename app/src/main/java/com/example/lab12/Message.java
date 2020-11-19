package com.example.lab12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.lab12.DataBase.DBHelper;

import java.util.ArrayList;


public class Message extends AppCompatActivity {

    TextView textView,textViewMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        textView = findViewById(R.id.subjectView);
        textViewMsg = findViewById(R.id.textMsgSub);

        Intent Extra = new Intent();

        String id = Extra.getStringExtra("msgID");

        DBHelper dbHelper = new DBHelper(getApplicationContext());

        ArrayList msgList = dbHelper.readMsg(id);

        textView.setText(msgList.get(1).toString());
        textViewMsg.setText(msgList.get(0).toString());

    }
}