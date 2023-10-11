package com.example.astar_dz_two;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textViewUsers;
    private Button buttonCreateUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewUsers=findViewById(R.id.textViewUser);
        buttonCreateUser=findViewById(R.id.buttonCreateUser);
    }

    public void onClickCreateUser(View view) {
        Intent intent=new Intent(this,CreateUser.class);
        startActivity(intent);
    }
}