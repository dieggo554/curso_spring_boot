package com.example.rest.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.rest.entity.Profesor;

/*
 * La interfaz CrudRepository<T, ID> maneja la conxeión con la fuente de datos
 * y ofrece métodos para gestionar esos datos (guardar, guardar todos, buscar, borrar, ...).
 */
public interface IProfesorDao extends CrudRepository<Profesor, Long> {
	
	// Al poner findByXXX (XXX = nombre de la propiedade del objeto) crea el método automaticamente.
	public Profesor findByEmail(String email);
	
	// Se puede poner más de una propiedad para la busqueda, en mayusculas y con And entre propiedades
	public Profesor findByEmailAndPassword(String email, String password);
	
	//Puede devolver un Profesor o no (?)
	public Optional<Profesor> findById(Long id);
	
	//Podemos ejecutar consultas y pasarle parametros con ?n
	@Query("select p from Profesor p where p.id = ?1")
	public Profesor findByIdSQL(Long id);
	
}
