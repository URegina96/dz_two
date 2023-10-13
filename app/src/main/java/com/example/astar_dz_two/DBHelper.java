package com.example.astar_dz_two;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {//шаг-6 создается класс для работы с базой данных (можно открывать, создавать и добавлять бд)

    public static final int DATABASE_VERSION = 1; //шаг-6.3 создаются константы (public - для работы с базой данных)
    public static final String DATABASE_NAME = "users";
    public static final String TABLE_CONTACTS = "contacts";

    public static final String KEY_ID = "_id";    //для заголовков столбцов таблицы
    public static final String KEY_NAME = "name"; //для заголовков столбцов таблицы
    public static final String KEY_AGE = "age"; //для заголовков столбцов таблицы


    public DBHelper(@Nullable Context context) {    //6.2- реализовываем конструктор
        super(context, DATABASE_NAME, null, DATABASE_VERSION); //используем константы
    }

    @Override//шаг-6.1 реализуется два обязательных абстракных метода с аннотаций   @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {//метод вызывается при создании базы данных
        //шаг-6.4 реализация логики создания таблиц и заполнения данными при помощи специальных команд SQL
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_CONTACTS + " (" + KEY_ID
                + " INTEGER, " + KEY_NAME + " TEXT, " + KEY_AGE + " TEXT)");

    }

    @Override//шаг-6.1 реализуется два обязательных абстракных метода с аннотаций   @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {//метод вызывается при изменени в базе данных ( а именно удаление и востановление - это когда меняется версия бд)
        //шаг -6.5 запрос на дроп таблицы, затем можно вызвать метод создания новой версии таблицы с обновлённой структурой
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }
}


//6.2 в конструкторе суперкласса передаем параметры (контекст, имя базы данных, объект класса курсов factory (на данный момент заnullим его так как не будем использовать), версия базы данных)
//drop table -  запрос на уничтожение таблицы базы данных


//    Поиск решения проблемы с присвоением id пользователя, сейчас выдает значение +2 к предыдущему пользователю, вместо порядкового номера
//
//    public DBHelper(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        String createTableQuery = "CREATE TABLE " + TABLE_CONTACTS + " (" +
//                KEY_ID + " INTEGER PRIMARY KEY, " +
//                KEY_NAME + " TEXT, " +
//                KEY_AGE + " TEXT)";
//
//        sqLiteDatabase.execSQL(createTableQuery);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
//        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
//        onCreate(sqLiteDatabase);
//    }