package com.minera.seleccion.entity;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "tbformulario")
public class Formulario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "edad")
	private int edad;
	
	@Column(name = "area")
	private String area;
	
	@Column(name = "puesto_actual")
	private String puestoActual;
	
	@Column(name = "anios_puesto")
	private int aniosPuesto;
	
	@Column(name = "puesto_postula")
	private String puestoPostula;
	
	@Column(name = "motivo_postula")
	private String motivoPostula;
	
	@Column(name = "fecha_postula")
	private Date fechaPostula;
	
	@Column(name = "estado")
	private String estado;
}
