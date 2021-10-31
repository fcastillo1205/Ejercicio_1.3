package com.example.pm01sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pm01sqlite.configuracion.SQLiteConexion;
import com.example.pm01sqlite.configuracion.transaccion;

public class ActivityConsulta extends AppCompatActivity {
    SQLiteConexion conexion;
    EditText id, nombre, apellido, edad, correo,direccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        conexion = new SQLiteConexion(this, transaccion.NameDatabase, null, 1);

        // Botones
        Button btnconsulta = (Button) findViewById(R.id.btnbuscar);
        Button btneliminar = (Button) findViewById(R.id.btneliminar);
        Button btnactualizar = (Button) findViewById(R.id.btnactualizar);

        id = (EditText) findViewById(R.id.txtcid);
        nombre = (EditText) findViewById(R.id.txtcnombres);
        apellido = (EditText) findViewById(R.id.txtcapellidos);
        edad = (EditText) findViewById(R.id.txtcedad);
        correo = (EditText) findViewById(R.id.txtccorreo);
        direccion = (EditText) findViewById(R.id.txtcdireccion);

        btnconsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Buscar();
            }
        });

        btnactualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Actualizar();
            }
        });

        btneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Eliminar();
            }
        });
}
    private void Buscar() {

        SQLiteDatabase db = conexion.getWritableDatabase();

        /* Parametros de configuracion de la sentencia SELECT */
        String [] params = {id.getText().toString()}; // parametro de la busqueda
        String [] fields = {transaccion.nombre,
                transaccion.apellido,
                transaccion.correo,
                transaccion.edad,
                transaccion.direccion
        };
        String wherecond = transaccion.id + "=?";

        try{
            Cursor cdata = db.query(transaccion.tablaPersonas, fields, wherecond, params, null,null, null );
            cdata.moveToFirst();
            nombre.setText(cdata.getString(0));
            apellido.setText(cdata.getString(1));
            correo.setText(cdata.getString(2));
            edad.setText(cdata.getString(3));
            direccion.setText(cdata.getString(4));

            Toast.makeText(getApplicationContext(), "Consultado con exito",Toast.LENGTH_LONG).show();
        }

        catch (Exception ex){
            ClearScreen();
            Toast.makeText(getApplicationContext(), "Elemento no encontrado",Toast.LENGTH_LONG).show();
            }
         }

    private void ClearScreen() {
        nombre.setText("");
        apellido.setText("");
        edad.setText("");
        correo.setText("");
        direccion.setText("");
    }

    private void Eliminar() {
        SQLiteDatabase db = conexion.getWritableDatabase();
        String [] params = {id.getText().toString()};
        String wherecond = transaccion.id + "=?";
        db.delete(transaccion.tablaPersonas, wherecond, params);
        Toast.makeText(getApplicationContext(), "Dato eliminado", Toast.LENGTH_LONG).show();
        ClearScreen();
    }

    private void Actualizar() {
        SQLiteDatabase db = conexion.getWritableDatabase();
        String [] params = {id.getText().toString()};

        ContentValues valores = new ContentValues();
        valores.put(transaccion.nombre, nombre.getText().toString());
        valores.put(transaccion.apellido, apellido.getText().toString());
        valores.put(transaccion.edad, edad.getText().toString());
        valores.put(transaccion.correo, correo.getText().toString());
        valores.put(transaccion.direccion, direccion.getText().toString());

        db.update(transaccion.tablaPersonas, valores, transaccion.id + "=?", params);
        Toast.makeText(getApplicationContext(), "Dato actualizado", Toast.LENGTH_LONG).show();
        ClearScreen();
    }

}

