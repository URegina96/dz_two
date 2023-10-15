package com.example.astar_dz_two;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


/**
 * Адаптер нужен для того что бы отобразить данные в виде списка. Сам RecyclerView понятия не
 * имеет как ему отображать данные, потому что структура данных может быть разная, и для того
 * что бы RecyclerView понимал как ему отображать список - делается адаптер.
 */
public class UsersAdapter extends RecyclerView.Adapter<UserViewHolder> {

    // Список пользоваетелей для отображения
    private final List<User> users = new ArrayList<>();
    // Инфлейтер нужен для того что бы "превратить" макет элемента списка в представление View
    private LayoutInflater inflater;

    /**
     * Этот метод просто обновляет список новыми данными
     */
    @SuppressLint("NotifyDataSetChanged")
    public void update(List<User> newUsers) {
        this.users.clear();
        this.users.addAll(newUsers);
        notifyDataSetChanged();
    }

    /**
     * Этот метод вызывается когда адаптер "прикрепляется" к RecyclerView
     * @param recyclerView это элемент отображения списка, к которому прикреплен данный адаптер
     */
    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        // инифиализируем инфлейтер
        inflater = LayoutInflater.from(recyclerView.getContext());
    }

    /**
     * Этот метод вызывается когда создается представление элемента списка
     */
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    /**
     * А этот метод вызывается когда к созданному представлению элемента списка привязываются
     * нужные данные для отображения
     * */
    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = users.get(position);
        holder.textId.setText("" + user.getId());  // отображаем ID
        holder.textName.setText(user.getName());   // отображаем имя
        holder.textAge.setText("" + user.getAge());// отображаем возраст
    }

    /**
     * Просто возвращаем количество элементов в списке
     */
    @Override
    public int getItemCount() {
        return users.size();
    }
}


/**
 * Класс который хранит состояние представления элементов списка
 */
class UserViewHolder extends RecyclerView.ViewHolder {
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
