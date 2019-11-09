package com.example.rest.service;

import java.util.List;

import com.example.rest.entity.Curso;

public interface ICursoService {
	
	public List<Curso> findAll();
	
	public void saveCurso(Curso curso);
	
	public List<Curso> getCursosProfesor(Long id);
}
