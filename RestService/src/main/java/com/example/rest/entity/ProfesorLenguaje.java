package com.example.rest.entity;

//Creamos una clase que une profesor y un lenguaje para recibirla
//Podríamos recibir ambas cosas y hacerlo manualmente
public class ProfesorLenguaje {

	private Profesor profesor;
	private Lenguaje lenguaje;
	public Profesor getProfesor() {
		return profesor;
	}
	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}
	public Lenguaje getLenguaje() {
		return lenguaje;
	}
	public void setLenguaje(Lenguaje lenguaje) {
		this.lenguaje = lenguaje;
	}
}
