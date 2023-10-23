package com.example.astar_dz_two;

import androidx.annotation.StringRes;

public interface ResourceProvider { //шаг 29 - Класс ResourceProvider обеспечивает легкий доступ к строковым ресурсам в приложениях Android.
    // Он предоставляет методы для получения строковых значений на основе их идентификатора ресурса и для форматирования строк с дополнительными аргументами, если это необходимо.
    // Это может быть полезно для таких задач, как отображение локализованного текста или создание динамических строк для пользовательских интерфейсов
    String string(@StringRes int resource);
    String string(@StringRes int resource, Object... args);
}