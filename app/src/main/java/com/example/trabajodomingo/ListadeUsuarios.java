package com.example.trabajodomingo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListadeUsuarios extends AppCompatActivity {

    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listade_usuarios);

        lista= findViewById(R.id.ListaUsuarios);

        // Recuperar la lista de usuarios del Intent
        ArrayList<String> listaUsuarios = getIntent().getStringArrayListExtra("listaUsuarios");

        // Crear un ArrayAdapter y establecerlo en el ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaUsuarios);
        lista.setAdapter(adapter);
    }
}