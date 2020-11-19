package com.example.lab12;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lab12.DataBase.DBHelper;

public class Register extends AppCompatActivity {

    EditText un,pw;
    CheckBox teacher,student;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        un = findViewById(R.id.unRegister);
        pw = findViewById(R.id.pwRegister);

        teacher = findViewById(R.id.teacherCheck);
        student = findViewById(R.id.stdCheck);

        button = findViewById(R.id.BtnRegisterForm);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type;
                if(teacher.isChecked()){
                    type = "teacher";
                }
                else
                    type = "student";

                DBHelper dbHelper = new DBHelper(getApplicationContext());

                boolean addUser = dbHelper.addInfoUser(un.getText().toString().trim(),pw.getText().toString().trim(),type);

                if(addUser){
                    Toast.makeText(getApplicationContext(), "User added", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getApplicationContext(), "User not added", Toast.LENGTH_SHORT).show();
            }
        });
    }
}