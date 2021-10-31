package com.example.pm01sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.pm01sqlite.configuracion.SQLiteConexion;
import com.example.pm01sqlite.configuracion.transaccion;
import com.example.pm01sqlite.tablas.Personas;

import java.util.ArrayList;

public class ActivityListView extends AppCompatActivity {

    //variables globales
    SQLiteConexion conexion;
    ListView listapersonas;
    ArrayList<Personas> lista;
    ArrayList<String> ArregloPersonas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        conexion = new SQLiteConexion(this, transaccion.NameDatabase, null, 1);
        listapersonas = (ListView) findViewById(R.id.lvListaPersonas);

        ObtenerListaPersonas();

        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ArregloPersonas);
        listapersonas.setAdapter(adp);

    }
    private void ObtenerListaPersonas(){
        SQLiteDatabase db = conexion.getReadableDatabase();
        Personas list_personas = null;
        lista = new ArrayList<Personas>();

        //cursor de  bd: nos apoya a recorrer la informacion de la tabla a la cual consltamos
        Cursor cursor = db.rawQuery("SELECT * FROM " + transaccion.tablaPersonas , null);

        //recorrer la informacion del cursor
        while (cursor.moveToNext()){
            list_personas = new Personas();
            list_personas.setId(cursor.getInt( 0));
            list_personas.setNombre(cursor.getString( 1));
            list_personas.setApellido(cursor.getString(2));
            list_personas.setEdad(cursor.getInt( 3));
            list_personas.setCorreo(cursor.getString( 4));
            list_personas.setDireccion(cursor.getString( 5));
            lista.add(list_personas);
        }

        cursor.close();

        filllist();
    }
    private void filllist(){
        ArregloPersonas = new ArrayList<String>();
        for (int i = 0; i < lista.size(); i++){

            ArregloPersonas.add(lista.get(i).getId() + " | "
                        +lista.get(i).getNombre() + " | "
                        +lista.get(i).getApellido() + " | "
                    +lista.get(i).getEdad() + " | "
                    +lista.get(i).getCorreo() + " | "
                    +lista.get(i).getDireccion());
        }

    }
}