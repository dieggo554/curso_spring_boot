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

import com.example.rest.entity.Lenguaje;
import com.example.rest.service.ILenguajeService;
import com.fasterxml.jackson.annotation.JsonBackReference;

@RestController
@RequestMapping("/api")
public class LenguajeRestController {

	@Autowired
	private ILenguajeService lenguajeService;
	
	@GetMapping("/lenguajes")
	public ResponseEntity<?> listaLenguajes(){
		List<Lenguaje> listaLenguajes = lenguajeService.findAll();
		if (listaLenguajes != null || listaLenguajes.size() != 0) {
			return new ResponseEntity<>(listaLenguajes, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/crear_lenguaje")
	public ResponseEntity<?> agregarLenguaje(@RequestBody Lenguaje lenguaje){
		lenguajeService.saveLenguaje(lenguaje);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
}
