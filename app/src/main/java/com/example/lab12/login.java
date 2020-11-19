package com.example.lab12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lab12.DataBase.DBHelper;

import java.util.List;

public class login extends AppCompatActivity {

    Button reg, login;
    EditText un,pw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        reg = findViewById(R.id.BtnReg);
        login = findViewById(R.id.BtnLogin);
        un = findViewById(R.id.un);
        pw = findViewById(R.id.pw);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this,Register.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               DBHelper dbHelper = new DBHelper(getApplicationContext());

                List userType = dbHelper.login(un.getText().toString().trim(),pw.getText().toString().trim());

                if(!userType.isEmpty()){
                    if(userType.get(1).equals("student")){
                        Toast.makeText(getApplicationContext(), "Login successful as a Student", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(login.this,student.class);
                        startActivity(intent);
                    }
                    else if(userType.get(1).equals("teacher")){
                        Toast.makeText(getApplicationContext(), "Login successful as a teacher", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(login.this,Teacher.class);
                        startActivity(intent);
                    }
                }
                else
                    Toast.makeText(getApplicationContext(), "In-Valid user", Toast.LENGTH_SHORT).show();
            }

        });

    }




}