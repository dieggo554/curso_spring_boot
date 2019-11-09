package com.example.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rest.entity.Curso;
import com.example.rest.entity.Profesor;
import com.example.rest.service.ICursoService;

@RestController
@RequestMapping("/api")
public class CursoRestController {

	@Autowired
	private ICursoService cursoService;

	@GetMapping("/cursos")
	public ResponseEntity<?> listaCursos() {
		List<Curso> listaCursos = cursoService.findAll();
		if (listaCursos != null && listaCursos.size() != 0) {
			return new ResponseEntity<>(listaCursos, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/crear_curso")
	public ResponseEntity<?> agregarCurso(@RequestBody Curso curso) {
		//deberíamos comprobar que existe el id de profesor que nos pasan
		cursoService.saveCurso(curso);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	// Debería ser un GET
	@PostMapping("/cursos_profesor")
	public ResponseEntity<?> verCursosProfesor(@RequestBody Profesor profesor) {
		List<Curso> listaCursos = cursoService.getCursosProfesor(profesor.getId());
		if (listaCursos != null && listaCursos.size() != 0) {
			return new ResponseEntity<>(listaCursos, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
}
