package com.example.astar_dz_two;

import static android.view.View.inflate;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UserViewHolder> {//шаг 19 – создаем UsersAdapter
    private final List<User> users = new ArrayList<>(); // Список пользоваетелей для отображения

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //шаг 19.1 – метод вызывается, когда создается представление элемента списка
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {//шаг 19.2 – метод вызывается, когда к созданному представлению элемента списка привязываются нужные данные для отображения
        User user = users.get(position);
        holder.textId.setText("" + user.getId());  // отображаем ID
        holder.textName.setText(user.getName());   // отображаем имя
        holder.textAge.setText("" + user.getAge());// отображаем возраст

    }

    @Override
    public int getItemCount() {//шаг 19.3 – метод вызывается, когда просто возвращаем количество элементов в списке
        return 0;
    }

}

class UserViewHolder extends RecyclerView.ViewHolder {//шаг 19.4 – класс который хранит состояние представления элементов списка
    TextView textId;
    TextView textName;
    TextView textAge;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        textId = itemView.findViewById(R.id.text_user_id);
        textName = itemView.findViewById(R.id.text_name);
        textAge = itemView.findViewById(R.id.text_age);
    }
}


//Класс UsersAdapter наследуется от RecyclerView.Adapter для того, чтобы ты мог создавать адаптеры для отображения списков пользователей внутри RecyclerView. Наследование от RecyclerView.
//Adapter позволяет использовать все его функциональные возможности, такие как создание и управление представлениями элементов списка, обновление данных и обработка событий

//поэтапная инструкция по настройке класса UsersAdapter:
//
//Шаг 1: Импортирование необходимых зависимостей Убедитесь, что вы импортировали следующие зависимости в начале файла:
//
//        import android.view.LayoutInflater;
//        import android.view.View;
//        import android.view.ViewGroup;
//        import androidx.recyclerview.widget.RecyclerView;

//Шаг 2: Создание класса UsersAdapter Создайте класс UsersAdapter и унаследуйте его от RecyclerView.Adapter. Например:
//
//public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {
//    // ...
//}
//Шаг 3: Создание класса ViewHolder Создайте внутренний класс ViewHolder, который расширяет RecyclerView.ViewHolder. Этот класс будет содержать представление элемента списка. Например:
//
//public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        // ...
//    }
//
//    // ...
//}
//Шаг 4: Реализация методов адаптера Переопределите необходимые методы адаптера, такие как onCreateViewHolder, onBindViewHolder и getItemCount. Эти методы отвечают за создание представления элемента, заполнение данных и определение количества элементов в списке. Например:
//
//public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {
//    // ...
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        // Создание представления элемента списка
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
//        ViewHolder viewHolder = new ViewHolder(view);
//        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//        // Заполнение данных элемента списка
//        User user = userList.get(position);
//        holder.usernameTextView.setText(user.getUsername());
//    }
//
//    @Override
//    public int getItemCount() {
//        // Определение количества элементов в списке
//        return userList.size();
//    }
//
//    // ...
//}
//Шаг 5: Использование UsersAdapter Теперь вы можете