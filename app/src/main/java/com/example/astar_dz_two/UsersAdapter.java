package com.example.astar_dz_two;

import static android.view.View.inflate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class UsersAdapter extends RecyclerView.Adapter<UserViewHolder> {//шаг 19 – создаем UsersAdapter  (extends RecyclerView.Adapter<указывается тип ViewHolder - определенный тип списка>)
    private final List<User> users = new ArrayList<>(); // Список пользоваетелей для отображения

    private final LayoutInflater inflater;

    private final ResourceProvider resources;
    private OnDeleteUserListener onDeleteUserListener;

    public UsersAdapter(LayoutInflater inflater, ResourceProvider resources) { // используется для отображения списка пользователей в приложении Android. Объекты LayoutInflater и ResourceProvider передаются в класс в качестве параметров, что позволяет эффективно и динамично создавать элементы пользовательского интерфейса с использованием строковых ресурсов из приложения
        this.inflater = inflater;
        this.resources = resources;
    }
    public void setOnDeleteUserListener(OnDeleteUserListener listener) {
        this.onDeleteUserListener = listener;
    }

        @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //шаг 19.1 – метод вызывается, когда создается представление элемента списка (тут создаются элементы ViewHolder)
        View view = inflater.inflate(R.layout.activity_item_user, parent, false); //всегда используем false
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {//шаг 19.2 – метод вызывается, когда к созданному представлению элемента списка привязываются нужные данные для отображения
        User user = users.get(position);
        holder.textId.setText(String.format(Locale.getDefault(),"%d",user.getId()));  // отображаем ID
        holder.textName.setText(resources.string(R.string.name_s, user.getName()));
        holder.textAge.setText(resources.string(R.string.age_d, user.getAge()));
        holder.imageDeleteUser.setOnClickListener(v -> {
            onDeleteUserListener.onDelete(user.getId());
        });
    }

    @Override
    public int getItemCount() {//шаг 19.3 – метод вызывается, когда просто возвращаем количество элементов в списке
        return users.size();//для того что бы адаптер понимал , сколько вообще ему надо создать элементов
    }

    public void update(List<User> newUsers) { //шаг - 21  Этот метод просто обновляет список новыми данными
        this.users.clear();
        this.users.addAll(newUsers);
        notifyDataSetChanged();
    }
}

class UserViewHolder extends RecyclerView.ViewHolder {//шаг 19.4 – класс который хранит состояние представления элементов списка   ( extends RecyclerView.ViewHolder )
    TextView textId;//
    TextView textName;
    TextView textAge;
    ImageView imageDeleteUser;

    public UserViewHolder(@NonNull View itemView) { //параметр itemView - один элемент списка (с данными одного user)
        super(itemView);
        textId = itemView.findViewById(R.id.text_user_id); //
        textName = itemView.findViewById(R.id.text_name);
        textAge = itemView.findViewById(R.id.text_age);
        imageDeleteUser = itemView.findViewById(R.id.button_delete_user);
    }
}

interface OnDeleteUserListener {
    void onDelete(long id);
}
