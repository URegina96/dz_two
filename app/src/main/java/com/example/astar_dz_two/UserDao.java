package com.example.astar_dz_two;

import java.util.List;

/**
 * Данный интерфейс просто предоставляет методы для действий с пользователями
 */
public interface UserDao {

    /**
     * Получить список всех пользователей
     */
    List<User> getUsers();

    /**
     * Создать нового пользователя.
     * @param user достаточно передать только имя и возраст
     */
    void createUser(User user);
}
