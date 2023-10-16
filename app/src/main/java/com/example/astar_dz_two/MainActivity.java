package com.example.astar_dz_two;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private UsersAdapter usersAdapter;
    private UserDao userDao;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupRecyclerView();
        setupButtons();
        setupDatabase();
        setupViewModel();
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewModel.reloadUsers();
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
        usersAdapter.setOnDeleteUserListener(new OnDeleteUserListener() {
            @Override
            public void onDelete(long id) {
                deleteUser(id);
            }
        });
    }

    private void deleteUser(long id) {
        userDao.deleteUser(id);
        List<User> users = userDao.getUsers();
        usersAdapter.update(users);
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

    private void setupViewModel() {
        ViewModelProvider.Factory factory = new MainViewModel.Factory(userDao);
        viewModel = new ViewModelProvider(this, factory).get(MainViewModel.class);
        viewModel.observeUsers(this, users -> {
            // обновление списка
            usersAdapter.update(users);
        });
    }
}