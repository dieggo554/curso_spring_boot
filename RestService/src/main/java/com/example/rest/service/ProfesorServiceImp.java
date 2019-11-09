package com.example.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.rest.dao.IProfesorDao;
import com.example.rest.entity.Profesor;

// Especificamos que es un servicio
@Service
public class ProfesorServiceImp implements IProfesorService {

	// Inyección de dependencias
	@Autowired
	private IProfesorDao profesorDao;
	
	@Override
	// Indicamos que es una transacción y de solo lectura
	//(se puede usar a nivel de clase con otros arguentos)
	@Transactional(readOnly = true)
	public List<Profesor> findAll() {
		return (List<Profesor>) profesorDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Profesor findProfesor(Profesor profesor) {
		// Buscará por el email
		return (Profesor) profesorDao.findByEmail(profesor.getEmail());
	}

	@Override
	@Transactional(readOnly = true)
	// Devuelve un Optional
	public Optional<Profesor> findProfesorById(Long id) {
		return (Optional<Profesor>) profesorDao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Profesor checkProfesorLogin(Profesor profesor) {
		return (Profesor) profesorDao.findByEmailAndPassword(profesor.getEmail(), profesor.getPassword());
	}

	@Override
	@Transactional
	public Profesor updateProfesor(Profesor profesor) {
		return (Profesor) profesorDao.save(profesor);
	}

	@Override
	@Transactional
	public void deteleProfesor(Profesor profesor) {
		profesorDao.deleteById(profesor.getId());
	}

	@Override
	@Transactional
	public void deteleProfesor(Long id) {
		profesorDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Profesor findById(Long id) {
		// Utiliza un método de Optional para devolver null si no lo encuentra
		return profesorDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Profesor findByIdSQL(Long id) {
		return profesorDao.findByIdSQL(id);
	}

	@Override
	@Transactional
	public void save(Profesor profesor) {
		profesorDao.save(profesor);
	}

	// Este método no está en nuestro DAO, viene dado por la implementación de la interfaz
	@Override
	@Transactional
	public void deteleAll() {
		profesorDao.deleteAll();
	}
}
