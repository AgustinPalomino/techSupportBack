/**
 * 
 */
package com.sumset.techsupport.models;

import java.sql.Date;

import jdk.jfr.DataAmount;

/**
 * @author SAgustín Palomino Pardo
 *
 */
public class CasosTablaDTO {
	
	public CasosTablaDTO() {
		super();
	}
	
	private Long id;
	private String asunto;
	private Date creacion;
	private String estado;
	private String observaciones;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public Date getCreacion() {
		return creacion;
	}
	public void setCreacion(Date creacion) {
		this.creacion = creacion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
}
