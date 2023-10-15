package com.example.astar_dz_two;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class CreateUserActivity extends AppCompatActivity {
    private EditText editTextUserName; //шаг-0 объявляем элементы экрана
    private EditText editTextUserAge;
    private UserDao userDao; //шаг-7 объявляем

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        setupViews();
        setupDatabase();
    }

    private void setupViews() {
        editTextUserName = findViewById(R.id.editTextUserName);
        editTextUserAge = findViewById(R.id.editTextUserAge);
        Button buttonSave = findViewById(R.id.buttonSave); //шаг-3 инициализируем
        buttonSave.setOnClickListener(v -> {
            createUser();
        });
    }

    private void setupDatabase() {
        userDao = DBHelper.getInstance(this);
    }

    private void createUser() {
        String name = editTextUserName.getText().toString().trim();
        String ageString = editTextUserAge.getText().toString().trim();

        if (!checkValidData(name, ageString)) {
            Toast.makeText(this, "Данные введены не корректно", Toast.LENGTH_SHORT).show();
            return;
        }

        int age = NumberUtils.stringToInt(ageString);
        User user = new User(name, age);
        userDao.createUser(user);

        Toast.makeText(this, "Пользователь добавлен", Toast.LENGTH_SHORT).show();
        finish();
    }

    private boolean checkValidData(String name, String age) {
        boolean nameValid = !name.isEmpty() && name.length() >= 3;
        boolean ageValid = !age.isEmpty() && NumberUtils.isNumber(age);
        return nameValid && ageValid;
    }

}