package com.example.astar_dz_two;

import android.annotation.SuppressLint;
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


/**
 * Адаптер нужен для того что бы отобразить данные в виде списка. Сам RecyclerView понятия не
 * имеет как ему отображать данные, потому что структура данных может быть разная, и для того
 * что бы RecyclerView понимал, как ему отображать список - делается адаптер.
 */
public class UsersAdapter extends RecyclerView.Adapter<UserViewHolder> {

    // Список пользователей для отображения
    private final List<User> users = new ArrayList<>();
    // Инфлейтер нужен для того что бы "превратить" макет элемента списка в представление View
    private final LayoutInflater inflater;

    private final ResourceProvider resources;

    private OnDeleteUserListener onDeleteUserListener;

    public UsersAdapter(LayoutInflater inflater, ResourceProvider resources) {
        this.inflater = inflater;
        this.resources = resources;
    }

    public void setOnDeleteUserListener(OnDeleteUserListener listener) {
        this.onDeleteUserListener = listener;
    }

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
     */
    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = users.get(position);
        holder.textId.setText(String.format(Locale.getDefault(), "%d", user.getId()));  // отображаем ID
        holder.textName.setText(resources.string(R.string.name_s, user.getName()));   // отображаем имя
        holder.textAge.setText(resources.string(R.string.age_d, user.getAge()));         // отображаем возраст
        holder.imageDeleteUser.setOnClickListener(v -> {
            onDeleteUserListener.onDelete(user.getId());
        });
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
    ImageView imageDeleteUser;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        textId = itemView.findViewById(R.id.text_user_id);
        textName = itemView.findViewById(R.id.text_name);
        textAge = itemView.findViewById(R.id.text_age);
        imageDeleteUser = itemView.findViewById(R.id.button_delete_user);
    }

}

interface OnDeleteUserListener {
    void onDelete(long id);
}
