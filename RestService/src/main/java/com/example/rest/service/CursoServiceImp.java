package com.example.rest.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rest.dao.ICursoDao;
import com.example.rest.entity.Curso;

@Service
public class CursoServiceImp implements ICursoService {
	
	@Autowired
	private ICursoDao cursoDao;

	@Override
	@Transactional
	public List<Curso> findAll() {
		return (List<Curso>) cursoDao.findAll();
	}

	@Override
	public void saveCurso(Curso curso) {
		cursoDao.save(curso);
	}

	@Override
	public List<Curso> getCursosProfesor(Long id) {
		return (List<Curso>) cursoDao.findByProfesorID(id);
	}
}
