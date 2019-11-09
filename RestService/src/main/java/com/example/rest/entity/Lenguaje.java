package com.example.rest.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "lenguaje")
public class Lenguaje implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombre;

//	//Otro ejemplo de fecha
//	@Column(name="fecha_creacion")
//	// damos un formato a la fecha que veremos desde el api
//	@JsonFormat(pattern="YYYY-MM-dd")

	// Campo recomendado
	@Column(name = "fecha_creacion")
	// Mapea fechas (DATE, TIME, TIMESTAMP) facilmente a la base de datos
	@Temporal(TemporalType.DATE)
	private Date fechaCreacion;

	@PrePersist
	public void prePersist() {
		fechaCreacion = new Date();
	}

	@ManyToMany
	// @JoinTable(name = "tabla intermedia")
	// @JoinColumn(name="nombre_bd_id-recomendadaConstante",
	// referencedColumnName="nombreEntityId"))
	@JoinTable(name = "profesores_lenguajes", joinColumns = @JoinColumn(name = "lenguaje_id", referencedColumnName = "id"), // this
			inverseJoinColumns = @JoinColumn(name = "profesor_id", referencedColumnName = "id"))
	private Set<Profesor> profesor = new HashSet<Profesor>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Set<Profesor> getProfesor() {
		return profesor;
	}

	public void setProfesor(Set<Profesor> profesor) {
		this.profesor = profesor;
	}

	private static final long serialVersionUID = 1L;
}
