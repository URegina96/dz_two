package com.example.astar_dz_two;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private UserDao userDao;
    private UsersAdapter usersAdapter;

    //шаг 20 – setupRecyclerView();setupButtons();setupDatabase(); в  class MainActivity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupRecyclerView();
        setupButtons();
        setupDatabase();
        List<User> users = userDao.getUsers();// получение списка пользователей из базы данных

        usersAdapter.update(users); // отображение списка пользователей в списке , для него в адаптере добавляем метод шаг - 21
    }

    private void setupDatabase() {  //шаг - 20.3 Настраиваем базу данных
        userDao = DBHelper.getInstance(this); // получаем объект базы данных
    }

    private void setupButtons() {  //шаг - 20.2 Настраиваем кнопку
        Button buttonCreateUser = findViewById(R.id.buttonCreateUser); //шаг -1 инициализируем
        buttonCreateUser.setOnClickListener(v -> { // шаг-2.1 создастся метод-слушатель кнопки
            Intent intent = new Intent(this, CreateUserActivity.class);
            startActivity(intent);
        });
    }

    private void setupRecyclerView() { //шаг - 20.1 Настраиваем список пользователей
        LinearLayoutManager manager = new LinearLayoutManager(this); // создаем менеджер компоновки, чтобы элементы списка отображались вертикально
        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);    // создаем разделители между элементами списка
        usersAdapter = new UsersAdapter();  // создаем адаптер для отображения списка пользователей
        RecyclerView recyclerUsers = findViewById(R.id.recyclerUsers);
        recyclerUsers.setLayoutManager(manager);
        recyclerUsers.addItemDecoration(decoration);
        recyclerUsers.setAdapter(usersAdapter);
    }
}


// private void setupRecyclerView() { } создается  для теста
