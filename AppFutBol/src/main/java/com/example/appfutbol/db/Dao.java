package com.example.appfutbol.db;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz genérica para operaciones CRUD (Create, Read, Update, Delete).
 * Define los métodos básicos que deben implementar los DAO para interactuar con cualquier entidad.
 *
 * @param <T> Tipo de entidad con la que trabajará el DAO.
 *
 * @author MartinAR
 */
public interface Dao<T> {

    /**
     * Busca un registro por su ID.
     *
     * @param id Identificador único del registro.
     * @return Un Optional que puede contener el objeto si se encuentra, o estar vacío si no existe.
     */
    Optional<T> findById(int id);

    /**
     * Recupera todos los registros del tipo T.
     *
     * @return Lista con todos los registros disponibles.
     */
    List<T> findAll();

    /**
     * Guarda un nuevo registro en la base de datos.
     *
     * @param record Objeto a guardar.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    boolean save(T record);

    /**
     * Actualiza un registro existente en la base de datos.
     *
     * @param record Objeto con los datos actualizados.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    boolean update(T record);

    /**
     * Elimina un registro por su ID.
     *
     * @param id Identificador del registro a eliminar.
     * @return true si el registro fue eliminado, false si no se encontró o falló la operación.
     */
    boolean delete(int id);
}

