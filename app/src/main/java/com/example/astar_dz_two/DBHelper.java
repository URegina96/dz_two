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


    public DBHelper(@Nullable Context context, @Nullable String name, int version) {    //6.2- реализовываем конструктор
        super(context, name, null, version);
    }

    @Override//шаг-6.1 реализуется два обязательных абстракных метода с аннотаций   @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {//метод вызывается при создании базы данных

    }

    @Override//шаг-6.1 реализуется два обязательных абстракных метода с аннотаций   @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {//метод вызывается при изменени в базе данных ( а именно удаление и востановление - это когда меняется версия бд)

    }


}













//6.2 в конструкторе суперкласса передаем параметры (контекст, имя базы данных, объект класса курсов factory (на данный момент заnullим его так как не будем использовать), версия базы данных)