package com.example.rest.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rest.entity.Lenguaje;
import com.example.rest.entity.Profesor;
import com.example.rest.entity.ProfesorLenguaje;
import com.example.rest.service.ILenguajeService;
import com.example.rest.service.IProfesorService;

@RestController
@RequestMapping("/api")
public class ProfesorLenguajeRestController {

	@Autowired
	private ILenguajeService lenguajeService;
	
	@Autowired
	private IProfesorService profesorService;
	
	@PostMapping("/lenguajes_profesor")
	public ResponseEntity<?> listaLenguajesProfesor(@RequestBody Profesor profesor) {
		Profesor profesorDb = profesorService.findById(profesor.getId());
		if (profesorDb != null) {
			Collection<Lenguaje> listaLenguajes = profesorDb.getLenguajes();
			if (listaLenguajes != null) {
				return new ResponseEntity<>(listaLenguajes, HttpStatus.OK);
			}
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	//Creamos una clase que une profesor y un lenguaje para recibirla aqui
	//Podríamos recibir ambas cosas y hacerlo manualmente
	@PostMapping("/save_lenguaje_profesor")
	public ResponseEntity<?> saveLenguajeProfesor(@RequestBody ProfesorLenguaje profesorLenguaje){
		Profesor profesorDb = profesorService.findById(profesorLenguaje.getProfesor().getId());
		if (profesorDb != null) {
			//deberíamos comprobar que existe el lenguaje
			Lenguaje lenguaje = lenguajeService.findLenguajeById(profesorLenguaje.getLenguaje().getId());
			profesorDb.addLenguaje(lenguaje);
			profesorService.save(profesorDb);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
}
