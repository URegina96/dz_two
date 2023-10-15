package com.example.astar_dz_two;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private UsersAdapter usersAdapter;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupRecyclerView();
        setupButtons();
        setupDatabase();

        // получение списка пользователей из базы данных
        List<User> users = userDao.getUsers();
        // отображение списка пользователей в списке
        usersAdapter.update(users);
    }

    /**
     * Настраиваем список пользователей
     */
    private void setupRecyclerView() {
        // создаем менеджер компоновки, чтобы элементы списка отображались вертикально
        LinearLayoutManager manager = new LinearLayoutManager(this);
        // создаем разделители между элементами списка
        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        // создаем обертку над context для предоставления ресурсов
        ResourceProvider resourceProvider = new ResourceProviderImpl(this);
        // создаем адаптер для отображения списка пользователей
        usersAdapter = new UsersAdapter(getLayoutInflater(), resourceProvider);
        RecyclerView recyclerUsers = findViewById(R.id.recyclerUsers);
        recyclerUsers.setLayoutManager(manager);
        recyclerUsers.addItemDecoration(decoration);
        recyclerUsers.setAdapter(usersAdapter);
    }

    /**
     * Настраиваем кнопку
     */
    private void setupButtons() {
        Button buttonCreateUser = findViewById(R.id.buttonCreateUser);//шаг -1 инициализируем
        buttonCreateUser.setOnClickListener(v -> {
            Intent intent = new Intent(this, CreateUserActivity.class);
            startActivity(intent);
        });
    }

    /**
     * Настраиваем базу данных
     */
    private void setupDatabase() {
        userDao = DBHelper.getInstance(this);    // получаем объект базы данных
    }

}