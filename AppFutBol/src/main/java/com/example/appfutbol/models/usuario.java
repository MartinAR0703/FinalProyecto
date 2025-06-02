package com.example.appfutbol.models;

/**
 * Representa un usuario de la aplicación con sus datos básicos: id, nombre, contraseña y correo.
 *
 * @author MartinAR
 */
public class usuario {
    private int id_usuario;
    private String nombre;
    private String password;
    private String correo;

    /**
     * Constructor completo para crear un usuario con todos sus datos.
     *
     * @param id_usuario Identificador único del usuario.
     * @param nombre Nombre del usuario.
     * @param password Contraseña del usuario.
     * @param correo Correo electrónico del usuario.
     */
    public usuario(int id_usuario, String nombre, String password, String correo) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.password = password;
        this.correo = correo;
    }

    /**
     * Constructor vacío para crear un usuario sin datos iniciales.
     */
    public usuario() {}

    /**
     * Obtiene el identificador del usuario.
     *
     * @return id del usuario.
     */
    public int getId_usuario() {
        return id_usuario;
    }

    /**
     * Establece el identificador del usuario.
     *
     * @param id_usuario Nuevo id del usuario.
     */
    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    /**
     * Obtiene el nombre del usuario.
     *
     * @return Nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del usuario.
     *
     * @param nombre Nuevo nombre del usuario.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return Contraseña del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param password Nueva contraseña del usuario.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Obtiene el correo electrónico del usuario.
     *
     * @return Correo electrónico del usuario.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Establece el correo electrónico del usuario.
     *
     * @param correo Nuevo correo electrónico del usuario.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
