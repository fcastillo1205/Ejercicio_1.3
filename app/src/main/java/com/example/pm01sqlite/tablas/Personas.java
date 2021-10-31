package com.example.pm01sqlite.tablas;

public class Personas {

    private Integer id;
    private String nombre;
    private String apellido;
    private Integer edad;
    private String correo;
    private String direccion;

    //PRIMER CONSTRUCTOR DE CLASE

    public Personas(Integer id, String nombre, String apellido, Integer edad, String correo, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.correo = correo;
        this.direccion = direccion;
    }

    //segundo constructor
    public Personas(){

    }

    public void setId(Integer id) {
        this.id = id;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public void setEdad(Integer edad) {
        this.edad = edad;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Integer getEdad() {
        return edad;
    }

    public String getCorreo() {
        return correo;
    }

    public String getDireccion() {
        return direccion;
    }
}
