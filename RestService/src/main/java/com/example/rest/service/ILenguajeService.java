package com.example.rest.service;

import java.util.List;

import com.example.rest.entity.Lenguaje;

public interface ILenguajeService {

	public List<Lenguaje> findAll();
	
	public void saveLenguaje(Lenguaje lenguaje);
	
	public Lenguaje findLenguajeById(Long id);
	
}
