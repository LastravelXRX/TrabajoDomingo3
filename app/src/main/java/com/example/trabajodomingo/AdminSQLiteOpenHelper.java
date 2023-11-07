package com.example.trabajodomingo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    @Override
    public void onCreate(SQLiteDatabase Produccion) {
        Produccion.execSQL("Create table Casas(idUsuario int primary key, RutUsuario Text, NombreUsuario Text, TelefonoUsuario Text, Direccion Text, Habitaciones Text, Baños Text)");
    }

    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}