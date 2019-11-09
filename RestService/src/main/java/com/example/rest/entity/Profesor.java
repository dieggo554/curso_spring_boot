package com.example.rest.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Dependencias Spring
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
//

// Etiqueta de entidad
@Entity
// Etiqueta para indicar la tabla correspondiente (si conicide el nombre no es necesaria)
@Table(name = "profesores")
public class Profesor implements Serializable {

	// Clave primaria en la base de datos
	@Id
	// Como se genera el valor de este campo
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Etiqueta para indicar la columna correspondiente (si conicide el nombre no es
	// necesaria)
	@Column(name = "nombre")
	private String nombre;

	// Tamaño máximo del valor del campo (ej: 60 caracteres, String)
	// Campo único, no se puede repetir
	@Column(length = 60, unique = true)
	private String email;

	private String password;

	@Column(length = 2000)
	private String foto;

	// Campo recomendado
	@Column(name = "fecha_creacion")
	// Mapea fechas (DATE, TIME, TIMESTAMP) facilmente a la base de datos
	@Temporal(TemporalType.DATE)
	private Date fechaCreacion;

	// Relación: un Profesor tiene 0 o varios cursos, cada curso tiene un solo
	// profesor
	// En relacional se añade un campo a la tabla cursos con el id del profesor
	// En orientado a objetos se añade un array con los cursos de cada profesor y el
	// id de profesor al curso
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "profesor_id", referencedColumnName = "id")
	private List<Curso> curso = new ArrayList<>();
	
	@ManyToMany(cascade = CascadeType.ALL)
	// evita un bucle infinito, indica que el json tiene una referencia a si mismo
	@JsonBackReference
	// recomendadaConstante
	@JoinTable(name = "profesores_lenguajes",
			joinColumns = @JoinColumn(name = "profesor_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name="lenguaje_id"))
	private Set<Lenguaje> lenguaje = new HashSet<Lenguaje>();
	
	// Obtiene la fecha actual al crear objeto automaticamente
	@PrePersist
	public void prePersist() {
		fechaCreacion = new Date();
	}

	public List<Curso> getCurso() {
		return curso;
	}

	public void setCurso(List<Curso> curso) {
		this.curso = curso;
	}

	// GETTERS & SETTERS
	
	//añadimos un lenguaje desde el controller ProfesorLenguaje
	public void addLenguaje(Lenguaje lenguaje) {
		this.lenguaje.add(lenguaje);
	}
	
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	public Set<Lenguaje> getLenguajes() {
		return lenguaje;
	}

	public void setLenguaje(Set<Lenguaje> lenguaje) {
		this.lenguaje = lenguaje;
	}
	//

	/*
	 * El serialVersionUID es un identificador de versión universal para una clase
	 * Serializable. La deserialización utiliza este número para garantizar que una
	 * clase cargada se corresponda exactamente con un objeto serializado. Si no se
	 * encuentra ninguna coincidencia, se lanza una excepción InvalidClassException.
	 */
	private static final long serialVersionUID = 1L;
}
