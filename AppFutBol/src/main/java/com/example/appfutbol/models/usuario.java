package com.example.appfutbol.models;

public class usuario {
    private int id_usuario;
    private String nombre;
    private String password;
    private String correo;

    public usuario(int id_usuario, String nombre, String password, String correo) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.password = password;
        this.correo = correo;
    }
    public usuario() {}
    public int getId_usuario() {return id_usuario;}
    public String getNombre() {return nombre;}
    public String getPassword() {return password;}
    public String getCorreo() {return correo;}

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
}