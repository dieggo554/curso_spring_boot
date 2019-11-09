package com.example.rest.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// Debería tener un campo fecha_creación
// El curso_id debería ser cursoID en Java

@Entity
@Table(name = "cursos")
public class Curso implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long curso_id;

	private String nombre;

	@Column(name = "profesor_id")
	private Long profesorID;

	public Curso() {}

	public long getCurso_id() {
		return curso_id;
	}

	public void setCurso_id(long curso_id) {
		this.curso_id = curso_id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getProfesorID() {
		return profesorID;
	}

	public void setProfesorID(Long profesorID) {
		this.profesorID = profesorID;
	}

	private static final long serialVersionUID = 1L;
}
