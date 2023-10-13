package com.example.astar_dz_two;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateUser extends AppCompatActivity {
    private EditText editTextUserName; //шаг-0 объявляем элементы экрана
    private EditText editTextUserAge;
    private Button buttonSave;

    DBHelper dbHelper; //шаг-7 объявляем

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        editTextUserName = findViewById(R.id.editTextUserName);
        editTextUserAge = findViewById(R.id.editTextUserAge);
        buttonSave = findViewById(R.id.buttonSave); //шаг-3 инициализируем

        dbHelper=new DBHelper(this);  //шаг-7.1 создаем его экземпляр


        buttonSave.setOnClickListener(v -> { //шаг-4.1 добавляем слушателя, что бы отследить клик
            String name = editTextUserName.getText().toString();//шаг-5 получение значения из полей ввода
            String age = editTextUserAge.getText().toString();

            SQLiteDatabase database=dbHelper.getWritableDatabase(); //шаг-8 создаю объект класса  SQLiteDatabase, его название database

            ContentValues contentValues =new ContentValues(); //шаг-9 создаем объект класса, который используется для добавления новых строк в таблицу (каждый объект этого класса  представляет собой  строку таблицы и выглядит как массив с именами столбцов и значениями которые ему соответствует)
            //шаг-9.1 заполняется парами - имя поля и значение
            contentValues.put(DBHelper.KEY_NAME,name);
            contentValues.put(DBHelper.KEY_AGE,age);
            long id = database.insert(DBHelper.TABLE_CONTACTS, null, contentValues); //шаг-14 получаем id

//            database.insert(DBHelper.TABLE_CONTACTS,null,contentValues);//шаг-9.2 методом .insert вставляются подготовленные строки в таблицу

            if (!(name ==null) && !(age ==null)){ //шаг-11 запускаем условие, что если все строки не пустые, то предыдущая активность с введенными данными пользователя запускается
                Intent intent=new Intent(this, MainActivity.class);
                intent.putExtra("name", name); // Передаем значение nameв MainActivity
                intent.putExtra("age", age); // Передаем значение age в MainActivity
                intent.putExtra("id", id); //шаг-14.1 включаем значение идентификатора
                startActivity(intent);
            }
            Toast.makeText(getApplicationContext(), "Данные успешно сохранены", Toast.LENGTH_SHORT).show(); //шаг-12 добавляем toast для того что бы узнать (и показать пользователю) что данные сохранились

            if (id > 10) {//шаг-16 для проверки работы базы данных сделаем максимальное количество пользователей 60
//                Здесь мы используем метод getVersion(), чтобы получить текущую версию базы данных. Затем мы увеличиваем newVersion на 1 и передаем значения oldVersion и newVersion в метод onUpgrade
                int oldVersion = database.getVersion(); // Получаем текущую версию базы данных
                int newVersion = oldVersion + 1;
                dbHelper.onUpgrade(database, oldVersion, newVersion);//шаг-16.1 Вызов метода onUpgrade() для удаления таблицы и создания новой
            }
        });
        dbHelper.close(); //шаг-10 закрываем соединение с базой данных
    }
}





//.insert  - этот метод принимает имя таблицы и объект contentValues со вставляемыми значениями , второй аргумент метода используется при вставке в таблицы пустой строки, это сейчас не нужно

//    На MainActivity вы должны извлечь переданные данные с помощью getIntent().getStringExtra():
//
//        String name = getIntent().getStringExtra("name"); String age = getIntent().getStringExtra("age");
//
//        После получения этих данных, вы можете использовать их для отображения на вашей активности MainActivity