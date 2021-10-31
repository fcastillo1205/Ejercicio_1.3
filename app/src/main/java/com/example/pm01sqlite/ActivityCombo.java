package com.example.pm01sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.pm01sqlite.configuracion.SQLiteConexion;
import com.example.pm01sqlite.configuracion.transaccion;
import com.example.pm01sqlite.tablas.Personas;

import java.util.ArrayList;

public class ActivityCombo extends AppCompatActivity {

    //variables globales
    SQLiteConexion conexion;
    Spinner combopersonas;
    EditText txtnombre, txtapellido, txtedad;

    ArrayList<String> listapersonas;
    ArrayList<Personas> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combo);

        conexion = new SQLiteConexion(this, transaccion.NameDatabase, null, 1);
        combopersonas = (Spinner) findViewById(R.id.combopersonas);
        txtnombre = (EditText) findViewById(R.id.txtnombres);
        txtapellido = (EditText) findViewById(R.id.txtapellidos);
        txtedad = (EditText) findViewById(R.id.txtyear);

        ObtenerListaPersonas();

        ArrayAdapter<CharSequence> adp = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listapersonas);
        combopersonas.setAdapter(adp);

        combopersonas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                txtnombre.setText(lista.get(position).getNombre().toString());
                txtapellido.setText(lista.get(position).getApellido().toString());
                txtedad.setText(lista.get(position).getEdad().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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

            lista.add(list_personas);
        }

        cursor.close();
        fillCombo();
    }

    private void fillCombo() {
        listapersonas = new ArrayList<String>();
        for (int i =0; i< lista.size(); i++){
            listapersonas.add(lista.get(i).getNombre()+ " | "+
                              lista.get(i).getApellido()+ " | "+
                              lista.get(i).getEdad());
        }
    }
}