package com.example.trabajodomingo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText ID, Rut, Nombre, Telefono, Direccion, Habitaciones, Baños;

    ListView Lista;

    //Primera modificacion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ID = findViewById(R.id.txtIDUsuario);
        Rut = findViewById(R.id.txtRut);
        Nombre = findViewById(R.id.txtNombre);
        Telefono = findViewById(R.id.txtTelefono);
        Direccion = findViewById(R.id.txtDireccion);
        Habitaciones = findViewById(R.id.txtHabitaciones);
        Baños = findViewById(R.id.txtBaños);



    }


    public void Registrarusuario(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Produccion", null, 1);
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();
        String IDUsuario = ID.getText().toString();
        String RutUsuario = Rut.getText().toString();
        String NombreUsuario = Nombre.getText().toString();
        String TelefonoUsuario = Telefono.getText().toString();
        String DireccionCasa = Direccion.getText().toString();
        String HabitacionesCasa = Habitaciones.getText().toString();
        String BañosCasa = Baños.getText().toString();
        if (!IDUsuario.isEmpty() && !RutUsuario.isEmpty() && !NombreUsuario.isEmpty() && !TelefonoUsuario.isEmpty() && !DireccionCasa.isEmpty()
                && !HabitacionesCasa.isEmpty() && !BañosCasa.isEmpty()){
            ContentValues DatosUsuario = new ContentValues();
            DatosUsuario.put("idUsuario", IDUsuario);
            DatosUsuario.put("RutUsuario", RutUsuario);
            DatosUsuario.put("NombreUsuario", NombreUsuario);
            DatosUsuario.put("TelefonoUsuario", TelefonoUsuario);
            DatosUsuario.put("Direccion", DireccionCasa);
            DatosUsuario.put("Habitaciones", HabitacionesCasa);
            DatosUsuario.put("Baños", BañosCasa);
            BaseDatos.insert("Casas", null, DatosUsuario);
            BaseDatos.close();
            ID.setText("");
            Rut.setText("");

            Nombre.setText("");
            Telefono.setText("");
            Direccion.setText("");
            Habitaciones.setText("");
            Baños.setText("");

            Toast.makeText(this, "Se a registrado exitosamente",
                    Toast.LENGTH_SHORT).show();
            CargaUsuarios();
        } else {
            Toast.makeText(this, "No pueden haber campos vacios",
                    Toast.LENGTH_SHORT).show();
        }
    }


    public void BuscarUsuario(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Produccion", null, 1);
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();
        String IDUsuario = ID.getText().toString();

        if(!IDUsuario.isEmpty()){
            Cursor fila = BaseDatos.rawQuery("Select RutUsuario, NombreUsuario, TelefonoUsuario, Direccion, Habitaciones, Baños from Casas where idUsuario="+ IDUsuario, null);
            if(fila.moveToFirst()){
                Rut.setText(fila.getString(0));
                Nombre.setText(fila.getString(1));
                Telefono.setText(fila.getString(2));
                Direccion.setText(fila.getString(3));
                Habitaciones.setText(fila.getString(4));
                Baños.setText(fila.getString(5));
                BaseDatos.close();
            } else {
                Toast.makeText(this, "No se encontro el ID ingresado",
                        Toast.LENGTH_SHORT).show();
                BaseDatos.close();
            }

        } else {
            Toast.makeText(this, "Campo ID no puede estar vacio",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void ActualizarUsuario(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Produccion", null, 1);
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();
        String IDUsuario = ID.getText().toString();
        String RutUsuario = Rut.getText().toString();
        String NombreUsuario = Nombre.getText().toString();
        String TelefonoUsuario = Telefono.getText().toString();
        String DireccionCasa = Direccion.getText().toString();
        String HabitacionesCasa = Habitaciones.getText().toString();
        String BañosCasa = Baños.getText().toString();

        if (!IDUsuario.isEmpty() && !RutUsuario.isEmpty() && !NombreUsuario.isEmpty() && !TelefonoUsuario.isEmpty() && !DireccionCasa.isEmpty()
                && !HabitacionesCasa.isEmpty() && !BañosCasa.isEmpty()){
            ContentValues DatosUsuario = new ContentValues();
            DatosUsuario.put("RutUsuario", RutUsuario);
            DatosUsuario.put("NombreUsuario", NombreUsuario);
            DatosUsuario.put("TelefonoUsuario", TelefonoUsuario);
            DatosUsuario.put("Direccion", DireccionCasa);
            DatosUsuario.put("Habitaciones", HabitacionesCasa);
            DatosUsuario.put("Baños", BañosCasa);

            int Cantidad = BaseDatos.update("Casas", DatosUsuario,
                    "idUsuario="+ IDUsuario, null);
            BaseDatos.close();
            if (Cantidad == 1){
                Toast.makeText(this, "El registro se actualizo correctamente",
                        Toast.LENGTH_SHORT).show();
                ID.setText("");
                Rut.setText("");
                Nombre.setText("");
                Telefono.setText("");
                Direccion.setText("");
                Habitaciones.setText("");
                Baños.setText("");
                CargaUsuarios();
            } else {
                Toast.makeText(this, "No se encontro el ID ingresado", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "No pueden haber campos vacios", Toast.LENGTH_SHORT).show();
        }
    }

    public void EliminarUsuario(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Produccion", null, 1);
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();
        String IDUsuario = ID.getText().toString();
        if (!IDUsuario.isEmpty()){
            int Eliminar = BaseDatos.delete("Casas", "idUsuario="+ IDUsuario, null);
            BaseDatos.close();
            if(Eliminar == 1){
                Toast.makeText(this, "El registro se elimino correctamente",
                        Toast.LENGTH_SHORT).show();
                ID.setText("");
                Rut.setText("");
                Nombre.setText("");
                Telefono.setText("");
                Direccion.setText("");
                Habitaciones.setText("");
                Baños.setText("");
                CargaUsuarios();
            } else {
                Toast.makeText(this, "El ID que intenta eliminar no existe",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Campo ID usuario no puede estar vacio",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void CargaUsuarios() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Produccion", null, 1);
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();
        Cursor fila = BaseDatos.rawQuery("Select * from Casas", null);
        ArrayList<String> ListaUsuarios = new ArrayList<>();

        if (fila.moveToFirst()) {
            do {
                String IDusuario = fila.getString(0);
                String RutUsuario = fila.getString(1);
                String NombreUsuario = fila.getString(2);
                String TelefonoUsuario = fila.getString(3);
                String DireccionCasa = fila.getString(4);
                String HabitacionesCasa = fila.getString(5);
                String BañosCasa = fila.getString(6);

                String userInfo = "ID: " + IDusuario + " Rut: " + RutUsuario + " Nombre: " +
                        NombreUsuario + " Telefono: " + TelefonoUsuario + " Direccion: " + DireccionCasa + " Habitaciones: " + HabitacionesCasa + " Baños: " + BañosCasa;
                ListaUsuarios.add(userInfo);
            } while (fila.moveToNext());
        }

        BaseDatos.close();
        Intent intent = new Intent(this, ListadeUsuarios.class);
        intent.putStringArrayListExtra("listaUsuarios", ListaUsuarios);
        startActivity(intent);
    }

}