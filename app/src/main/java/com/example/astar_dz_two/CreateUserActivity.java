package com.example.astar_dz_two;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateUserActivity extends AppCompatActivity {
    private EditText editTextUserName; //шаг-0 объявляем элементы экрана
    private EditText editTextUserAge;
    private UserDao userDao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        setupViews();
        setupDatabase();


    }

    private void setupViews() { //шаг - 24   setupViews() обычно используется для инициализации и настройки пользовательского интерфейса (Views) в приложении. Например, здесь можно установить слушателей для кнопок, настроить адаптеры для списков или привязать данные к различным элементам интерфейса
        editTextUserName = findViewById(R.id.editTextUserName);
        editTextUserAge = findViewById(R.id.editTextUserAge);
        Button buttonSave = findViewById(R.id.buttonSave); //шаг-3 инициализируем
        buttonSave.setOnClickListener(v -> {
            createUser();
        });
    }

    private void setupDatabase() { //шаг - 24   setupDatabase() используется для настройки базы данных в приложении. Этот метод может включать создание базы данных, создание таблиц, определение структуры базы данных, проверку и обновление схемы, и другие действия, связанные с управлением базой данных
        userDao = DBHelper.getInstance(this);
    }

    private void createUser() { // шаг -25    используется для создания нового пользователя или записи в приложении. Он может включать в себя действия, такие как получение данных от пользователя, создание объекта User с этими данными и сохранение его в базе данных или другом хранилище данных
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

    private boolean checkValidData(String name, String age) { // шаг - 26    используется для проверки валидности данных, переданных в метод. Он может выполнять проверки на корректность формата имени и возраста, а также наличие всех необходимых данных Например, этот метод может проверять, что имя не является пустой строкой и что возраст является числом. Результатом этой проверки будет логическое значение (true или false), которое может использоваться для принятия решений в других частях кода, например, для отображения сообщений об ошибке или продолжения выполнения определенных действий
        boolean nameValid = !name.isEmpty() && name.length() >= 3;
        boolean ageValid = !age.isEmpty() && NumberUtils.isNumber(age);
        return nameValid && ageValid;
    }
}
