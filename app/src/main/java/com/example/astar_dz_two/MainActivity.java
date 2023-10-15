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
        // создаем адаптер для отображения списка пользователей
        usersAdapter = new UsersAdapter();
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


//getApplicationContext() - это метод, который вызывается для получения контекста приложения. Контекст предоставляет доступ к ресурсам и операциям, связанным с приложением.
//Контекст приложения представляет собой глобальный контекст, который действует на протяжении всего жизненного цикла приложения. Он может быть использован в различных ситуациях, например, для получения доступа к ресурсам приложения, запуска новых активностей и служителей, создания уведомлений и т. д.
//etApplicationContext() используется в примере для создания Intent, который необходим для запуска новой активности. Он требует контекста приложения в качестве первого аргумента. Вы можете использовать и другие методы для получения контекста, такие как this или ActivityName.this, в зависимости от контекста, в котором вы находитесь.
//Надеюсь, это помогло вам понять, что такое getApplicationContext() и зачем он нужен.


//       buttonCreateUser.setOnClickListener(new View.OnClickListener() {//шаг-2 добавляем (один из трех вариантов) слушателя кнопки
////Эта строка кода создает слушатель для кнопки с именем "buttonCreateUser". Когда пользователь нажимает на эту кнопку, будет выполнен код, который находится внутри метода onClick().
//// Обычно в этом методе размещается код, который создает нового пользователя или выполняет другие действия при нажатии кнопки
//@Override // шаг-2.1 переопределяется метод
//public void onClick(View view) { // шаг-2.2 создастся метод
//        Intent intent=new Intent(getApplicationContext(),CreateUser.class);//шаг-2.3 пользователь кликает по кнопке переходя в другую активность
//        startActivity(intent);
//        }
//        });

//SQLiteDatabase  - этот класс предназначен для управления базой данных SQLite
//getWritableDatabase() -  метод вспомогательного класса, что бы открыть и вернуть экземпляр базы данных с которым будем работать (доступен для чтения и записи), если бд не существует , то он вызывает свой метод onCreate, если изменилось то метод onUpgrade
