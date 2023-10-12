package com.example.astar_dz_two;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class CreateUser extends AppCompatActivity {
    private EditText editTextUserName; //шаг-0 объявляем элементы экрана
    private EditText editTextUserAge;
    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        editTextUserName = findViewById(R.id.editTextUserName);
        editTextUserAge = findViewById(R.id.editTextUserAge);
        buttonSave = findViewById(R.id.buttonSave); //шаг-3 инициализируем

        buttonSave.setOnClickListener(v -> { //шаг-4.1 добавляем слушателя, что бы отследить клик
            String name = editTextUserName.getText().toString();//шаг-5 получение значения из полей ввода
            String ege = editTextUserAge.getText().toString();
        });
    }
}