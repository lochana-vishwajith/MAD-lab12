package com.example.lab12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.lab12.DataBase.DBHelper;

import java.util.ArrayList;

public class student extends AppCompatActivity {

    ListView listView;
    ArrayAdapter adapter;
    ArrayList arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        listView = findViewById(R.id.list);

        DBHelper dbHelper = new DBHelper(getApplicationContext());

        arrayList = dbHelper.readAllMessage();

        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String msg = listView.getItemAtPosition(i).toString();
                Intent intent = new Intent(student.this,Message.class);
                intent.putExtra("msgID",msg);
                startActivity(intent);
            }
        });

    }
}