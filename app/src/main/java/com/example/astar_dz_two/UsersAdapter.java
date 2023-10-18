package com.example.astar_dz_two;

import static android.view.View.inflate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UserViewHolder> {//шаг 19 – создаем UsersAdapter //<*порядок создания - 2 (extends RecyclerView.Adapter<указывается тип ViewHolder - определенный тип списка>)*>
    private final List<User> users = new ArrayList<>(); // Список пользоваетелей для отображения       <*порядок создания - 4*>

    private LayoutInflater inflater;//<*порядок создания - 3.2.1*>

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //шаг 19.1 – метод вызывается, когда создается представление элемента списка (тут создаются элементы ViewHolder) <*порядок создания - 3*>
        //inflater.inflate - конвертирует (надувает) xml макета  в View представление макета (так как нам нужны элементы для ViewHolder)
        View view = inflater.inflate(R.layout.activity_item_user, parent, false); //всегда используем false         //<*порядок создания - 3.2.2*>
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {//шаг 19.2 – метод вызывается, когда к созданному представлению элемента списка привязываются нужные данные для отображения <*порядок создания - 3*>
        User user = users.get(position); //<*порядок создания - 3.3*>
        holder.textId.setText("" + user.getId());  // отображаем ID
        holder.textName.setText(user.getName());   // отображаем имя
        holder.textAge.setText("" + user.getAge());// отображаем возраст

    }

    @Override
    public int getItemCount() {//шаг 19.3 – метод вызывается, когда просто возвращаем количество элементов в списке <*порядок создания - 3*>
        return users.size();//для того что бы адаптер понимал , сколько вообще ему надо создать элементов  <*порядок создания - 3.1*>
    }
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) { //шаг 19.5 – метод вызывается, когда адаптер "прикрепляется" к RecyclerView; @param recyclerView это элемент отображения списка, к которому прикреплен данный адаптер //<*порядок создания - 5*>
        super.onAttachedToRecyclerView(recyclerView);

        inflater = LayoutInflater.from(recyclerView.getContext());
    }

    public void update(List<User> newUsers) { //шаг - 21  Этот метод просто обновляет список новыми данными
        this.users.clear();
        this.users.addAll(newUsers);
        notifyDataSetChanged();
    }
}

class UserViewHolder extends RecyclerView.ViewHolder {//шаг 19.4 – класс который хранит состояние представления элементов списка   <* порядок создания - 1 *> <*порядок создания - 1.1 ( extends RecyclerView.ViewHolder ) *>
    TextView textId;//<*порядок создания - 2.1.2*>
    TextView textName;
    TextView textAge;

    public UserViewHolder(@NonNull View itemView) { //параметр itemView - один элемент списка (с данными одного user)      <*порядок создания - 1.2*>
        super(itemView);
        textId = itemView.findViewById(R.id.text_user_id); //<*порядок создания - 2.1.1*>
        textName = itemView.findViewById(R.id.text_name);
        textAge = itemView.findViewById(R.id.text_age);
    }
}
