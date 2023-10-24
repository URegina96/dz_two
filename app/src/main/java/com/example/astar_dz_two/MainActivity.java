package com.example.astar_dz_two;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
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
    private MainViewModel viewModel;

    //шаг 20 – setupRecyclerView();setupButtons();setupDatabase(); в  class MainActivity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupRecyclerView();
        setupButtons();
        setupDatabase();
        setupViewModel();
    }
    @Override //  метод вызывается при запуске активности. Он вызывает метод reloadUsers() viewModel, который извлекает список пользователей из базы данных и обновляет объект userLiveData. Это гарантирует, что самые последние данные будут доступны для пользовательского интерфейса при запуске действия
    protected void onStart() {
        super.onStart();
        viewModel.reloadUsers();
    }

    private void setupViewModel() {
        ViewModelProvider.Factory factory = new MainViewModel.Factory(userDao);
        viewModel = new ViewModelProvider(this, factory).get(MainViewModel.class);
        viewModel.observeUsers(this, users -> {
            // обновление списка
            usersAdapter.update(users);
        });
        /* метод отвечает за настройку объекта viewModel. Он создает новый экземпляр MainViewModel.Factory, передавая объект userDao. Затем он использует эту фабрику для создания экземпляра класса MainViewModel с помощью ViewModelProvider
            Затем он устанавливает наблюдателя для объектаusersLiveData, вызывая метод ObserveUsers(). Этот наблюдатель обновляет пользовательский интерфейс всякий раз, когда меняется список пользователей
            Наконец, метод ObserveUsers() определяет лямбда-выражение, которое будет выполняться всякий раз, когда наблюдатель получает обновления. В этом случае он обновляет userAdapter последним списком пользователей   */
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
        Button buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonUpdate.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void setupRecyclerView() { //шаг - 20.1 Настраиваем список пользователей
        LinearLayoutManager manager = new LinearLayoutManager(this); // создаем менеджер компоновки, чтобы элементы списка отображались вертикально
        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);    // создаем разделители между элементами списка
        // создаем обертку над контекстом для предоставления ресурсов
        ResourceProvider resourceProvider = new ResourceProviderImpl(this);
        usersAdapter = new UsersAdapter(getLayoutInflater(), resourceProvider);// создаем адаптер для отображения списка пользователей
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
}


// private void setupRecyclerView() { } создается  для теста
