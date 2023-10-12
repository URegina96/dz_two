package com.example.astar_dz_two;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textViewUsers; //шаг-0 объявляем элементы экрана
    private Button buttonCreateUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewUsers = findViewById(R.id.textViewUser);
        buttonCreateUser = findViewById(R.id.buttonCreateUser);//шаг -1 инициализируем

        //шаг-2 добавляем (один из трех вариантов добавления) слушателя кнопки OnClick
        buttonCreateUser.setOnClickListener(view -> { // шаг-2.1 создастся метод-слушатель кнопки
            Intent intent = new Intent(getApplicationContext(), CreateUser.class);//шаг-2.2 пользователь кликает по кнопке переходя в другую активность
            startActivity(intent);
        });
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