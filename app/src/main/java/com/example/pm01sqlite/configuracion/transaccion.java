package com.example.pm01sqlite.configuracion;

public class transaccion {
    //nombre de la base de datos
    public static final String NameDatabase = "PMO1DB";

    //tablas de la DB en SQLite

    public static final String tablaPersonas = "personas";

    //campo de la tabla personas de la DB en SQLite
    public static final String id = "id";
    public static final String nombre = "nombre";
    public static final String apellido = "apellido";
    public static final String edad = "edad";
    public static final String correo = "correo";
    public static final String direccion = "direccion";

    //transacciones DDL(DATA DEFINITION LENGUAGE) tabla personas
    public static final String CreateTablePersonas = "CREATE TABLE personas (id INTEGER PRIMARY KEY AUTOINCREMENT "+
        ",nombre TEXT, apellido TEXT, edad INTEGER, correo TEXT, direccion TEXT)";
    public static final String DROPTablePersonas = "DROP TABLE IF EXISTS personas";

}
