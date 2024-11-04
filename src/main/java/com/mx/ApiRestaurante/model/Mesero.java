package com.mx.ApiRestaurante.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*CREATE TABLE MESERO(
ID NUMBER PRIMARY KEY,
NOMBRE VARCHAR2(70) NOT NULL,
APP VARCHAR2(70) NOT NULL,
APM VARCHAR2(70),
SUELDO NUMBER NOT NULL
);*/

@Entity
@Table(name = "MESERO")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Mesero {

	@Id
	private Long id;
	private String nombre;
	private String app;
	private String apm;
	private Float sueldo;
	
	//Cardinalidad 1:N
	
	@OneToMany (mappedBy = "mesero", cascade = CascadeType.ALL)
	@JsonIgnore
	List<Mesas> lista = new ArrayList<Mesas>();
}
