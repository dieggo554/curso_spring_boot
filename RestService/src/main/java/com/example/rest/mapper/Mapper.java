package com.example.rest.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.rest.entity.Profesor;
import com.example.rest.model.MProfesor;

// Con esta etiqueta Spring detecta esta clase como dependencia/componente/bean
@Component("mapper")
public class Mapper {

	// Utilizamos una lista aun que no siempre sea necesaria, podríamos crear otro método sin lista
	public static List<MProfesor> convertirLista(List<Profesor> profesores){
		List<MProfesor> mProfesores = new ArrayList<>();
		for (Profesor profesor : profesores) {
			mProfesores.add(new MProfesor(profesor));
		}
		return mProfesores;
	}
}
