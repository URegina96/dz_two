package com.example.astar_dz_two;

import java.util.List;

public interface UserDao { //шаг 18 – создаем UserDao,  Данный интерфейс просто предоставляет методы для действий с пользователями
    List<User> getUsers();//шаг 18.1 – получаем весь список пользователей

    void createUser(User user); //шаг 18.1 - создание нового пользователя;  @param user достаточно передать только имя и возраст
}
