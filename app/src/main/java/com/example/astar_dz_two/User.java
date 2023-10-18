package com.example.astar_dz_two;

import androidx.annotation.NonNull;

public class User {//шаг 17 – создаем User
    private long id; //шаг 17.1 – именуем переменные
    private String name;
    private int age;
    public User(String name, int age) { //шаг 17.2 – инициализируем /*для метода 21 в DBhelper делаем public*/
        this.name = name;
        this.age = age;
    }
    //шаг 17.3 – генерируем гет и сет
    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
    //шаг 17.4 – переопределяем метод toString(), чтобы передать строковое представление объекта User в формате
    @Override
    public String toString() {
        return "User{"+
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}


// методы для и установки значений идентификатора (id, name, age) в классе объекта
//Метод getId()получения текущего идентификатора объекта User. Например, если у вас есть объект User user, то user.getId()вернет текущее значение идентификатора этого пользователя.
//Метод setId(long id)установки нового идентификатора объекта User. Например, если у вас есть объект User user, user.setId(123)установите значение идентификатора пользователя в 123.
//Эти методы дают возможность и установку значений идентификатора объекта Userи являются частью его публичного интерфейса.


//В данном случае метод toString()извлекает результат, который содержит информацию об идентификаторе ( id), имени ( name) и возрасте ( age) пользователя. Формат этой строки выглядит следующим образом: «User{id=123, name='John Doe', age=25}».
//Переопределение toString()позволяет вам контролировать, как объекты класса Userбудут представлены в виде строк. Это полезно при отладке, выводе информации или просто для удобства чтения.