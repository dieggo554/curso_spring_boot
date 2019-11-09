package com.example.rest.service;

import java.util.List;
import java.util.Optional;

import com.example.rest.entity.Profesor;

// Funcionalidad / lógica de negocio, todos los metodos a utilizar deben estar aquí
public interface IProfesorService {

	public List<Profesor> findAll();
	
	public Profesor findProfesor(Profesor profesor);
	
	public Optional<Profesor> findProfesorById(Long id);
	
	public Profesor checkProfesorLogin(Profesor profesor);
	
	public Profesor updateProfesor(Profesor profesor);
	
	public void deteleProfesor(Profesor profesor);
	
	public void deteleProfesor(Long id);
	
	public void deteleAll();
	
	public Profesor findById(Long id);
	
	public Profesor findByIdSQL(Long id);
	
	// Intentamos usarlo desde ProfesorService pero da error al no existir aqui, lo creamos
	public void save(Profesor profesor);
}
