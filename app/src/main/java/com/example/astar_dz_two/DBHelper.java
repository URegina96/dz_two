package com.example.astar_dz_two;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper implements UserDao {//шаг-6 создается класс для работы с базой данных (можно открывать, создавать и добавлять бд)

    public static final int DATABASE_VERSION = 3; //шаг-6.3 создаются константы (public - для работы с базой данных)
    public static final String DATABASE_NAME = "users";
    public static final String TABLE_CONTACTS = "contacts";

    public static final String KEY_ID = "id";    //для заголовков столбцов таблицы
    public static final String KEY_NAME = "name"; //для заголовков столбцов таблицы
    public static final String KEY_AGE = "age"; //для заголовков столбцов таблицы

    private static DBHelper instance; //объявляем cтатичный объект базы данных для синглтона



    private DBHelper(@Nullable Context context) {    //6.2- реализовываем конструктор Конструктор для базы данных делаем private, чтобы было невозможно создать объект через конструктор new. Пользуемся методом getInstance для получения объекта базы данных
        super(context, DATABASE_NAME, null, DATABASE_VERSION); //используем константы
    }

    public static UserDao getInstance(Context context) { //шаг - 21   метод getInstance, который возвращает экземпляр UserDao
        if (instance == null) { // возвращаем Singleton объект базы данных в единственном экземпляре. Если instance базы данных NULL, то объект базы данных будет создан
            instance = new DBHelper(context);
        }
        return instance;
    }

    @Override//шаг-6.1 реализуется два обязательных абстракных метода с аннотаций   @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {//метод вызывается при создании базы данных
        //шаг-6.4 реализация логики создания таблиц и заполнения данными при помощи специальных команд SQL
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_CONTACTS + " (" + KEY_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME + " TEXT, " + KEY_AGE + " TEXT)");

    }

    @Override//шаг-6.1 реализуется два обязательных абстракных метода с аннотаций   @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {//метод вызывается при изменени в базе данных ( а именно удаление и востановление - это когда меняется версия бд)
        //шаг -6.5 запрос на дроп таблицы, затем можно вызвать метод создания новой версии таблицы с обновлённой структурой
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }

    @Override
    public List<User> getUsers() { //шаг - 22 в этом методе реализуем методы интерфейса UserDao для создания пользователя и получения списка всех пользователей из таблицы. Ну то есть мы убрали логику добавления и чтения из Activity, тем самым сделали код чище и безопаснее
        List<User> users = new ArrayList<>();
        Cursor cursor = getReadableDatabase().query(TABLE_CONTACTS, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int index = cursor.getColumnIndex(KEY_ID);
            long id = cursor.getLong(index);
            index = cursor.getColumnIndex(KEY_NAME);
            String name = cursor.getString(index);
            index = cursor.getColumnIndex(KEY_AGE);
            int age = cursor.getInt(index);
            User user = new User(name, age);
            user.setId(id);
            users.add(user);
        }
        cursor.close();
        return users;
    }
    @Override
    public void createUser(User user) { //шаг - 23  метод createUser, который принимает объект User в качестве параметра. Затем, внутри метода, мы создаем объект ContentValues, который используется для хранения пар ключ-значение. Здесь мы добавляем имя и возраст пользователя в ContentValues с помощью методов put(), где KEY_NAME и KEY_AGE предполагаются ключами для соответствующих значений \\
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getName());
        values.put(KEY_AGE, user.getAge());
        this.getWritableDatabase().insert(TABLE_CONTACTS, null, values);
    }
    // DELETE FROM table  WHERE id = :id
    /*метод для удаления пользователя по id*/
    @Override
    public void deleteUser(long id) {
        this.getWritableDatabase().delete(TABLE_CONTACTS, "id=?", new String[] {"" + id});
    }
}


//6.2 в конструкторе суперкласса передаем параметры (контекст, имя базы данных, объект класса курсов factory (на данный момент заnullим его так как не будем использовать), версия базы данных)
//drop table -  запрос на уничтожение таблицы базы данных
