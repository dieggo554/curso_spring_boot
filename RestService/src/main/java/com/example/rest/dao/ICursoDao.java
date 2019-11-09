package com.example.rest.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.rest.entity.Curso;

public interface ICursoDao extends CrudRepository<Curso, Long> {
	
	public List<Curso> findByProfesorID(Long id);

}
