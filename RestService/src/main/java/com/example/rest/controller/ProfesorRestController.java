package com.example.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.rest.entity.Profesor;
import com.example.rest.mapper.Mapper;
import com.example.rest.model.MProfesor;
import com.example.rest.service.IProfesorService;

// Indica que esta clase es un controlador de solucitudes web HTTPS para peticiones REST
@RestController
// Parte final de la URL (no dominio)
@RequestMapping("/api")
public class ProfesorRestController {

	// Inyección de dependencias
	@Autowired
	private IProfesorService profesorService;

	// Indicamos la URL
	@GetMapping("/profesores")
	// Codigo respuesta HTTP
	@ResponseStatus(HttpStatus.OK)
	public List<Profesor> getProfesores() {
		return profesorService.findAll();
	}

	@PostMapping("/sign_up")
	/*
	 * ResponseEntity nos puede enviar un objeto, lista, boolean, void, string,...
	 * como primer parámetro y un HTTP response code como segundo parametro: return
	 * new ResponseEntity<>( "Year of birth cannot be in the future",
	 * HttpStatus.BAD_REQUEST);
	 */
	public ResponseEntity<Void> addProfesor(@RequestBody Profesor profesor) {
		if (profesorService.findProfesor(profesor) == null) {
			profesorService.save(profesor);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
	}

	// Recibimos un parametro en la URL con {XXX} y lo recogemos con
	// @PathVariable(value="XXX")Type name
	@PutMapping("/update/{id}")
	// usamos "<?>" por que no sabemos que devolverá y así acepta cualquier cosa
	public ResponseEntity<?> updateProfesor(@PathVariable(value = "id") Long id, @RequestBody Profesor profesor) {
		Profesor profesorDb = null;
		profesorDb = profesorService.findById(id);
		if (profesorDb != null) {
			// Actualizamos el objeto solo con los campos que queramos
			// Podemos no recibir algun valor que se guardará como null
			profesorDb.setEmail(profesor.getEmail());
			profesorDb.setNombre(profesor.getNombre());
			// Actualizamos el objeto en la base de datos
			profesorService.updateProfesor(profesorDb);
			return new ResponseEntity<>(profesorDb, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteProfesor(@PathVariable(value = "id") Long id) {
		Optional<Profesor> profesorBd;
		// Convertimo profesor a optional
		profesorBd = Optional.ofNullable(profesorService.findById(id));
		// Comprobamos si está vacio
		if (profesorBd.isPresent()) {
			System.out.println("Existe");
			profesorService.deteleProfesor(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			System.out.println("No existe");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Void> deleteAllProfesor() {
		profesorService.deteleAll();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> loginProfesor(@RequestBody Profesor profesor){
		Profesor profesorDb = profesorService.checkProfesorLogin(profesor);
		if (profesorDb != null) {
			// Utilizamos el mapper
			List<Profesor> profesores = new ArrayList<>();
			profesores.add(profesorDb);
			List<MProfesor> mProfesores = new ArrayList<>();
			mProfesores = Mapper.convertirLista(profesores);
			//
			return new ResponseEntity<>(mProfesores, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		
	}
}
