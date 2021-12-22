/**
 * 
 */
package com.sumset.techsupport.models;

import java.sql.Date;
import java.util.List;

/**
 * @author Agustín Palomino Pardo
 *
 */
public class CasoConAdjuntos {
	
	public CasoConAdjuntos() {
		super();
	}
	
	private Long id;
	private Long solicita;
	private Long atiende;
	private String categoria;
	private String severidad;
	private String descripcion;
	private String estado;
	private Date creacion;
	private String asunto;
	private String tipo;
	private String observaciones;
	private List<Adjuntos> adjuntos;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSolicita() {
		return solicita;
	}
	public void setSolicita(Long solicita) {
		this.solicita = solicita;
	}
	public Long getAtiende() {
		return atiende;
	}
	public void setAtiende(Long atiende) {
		this.atiende = atiende;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getSeveridad() {
		return severidad;
	}
	public void setSeveridad(String severidad) {
		this.severidad = severidad;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Date getCreacion() {
		return creacion;
	}
	public void setCreacion(Date creacion) {
		this.creacion = creacion;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public List<Adjuntos> getAdjuntos() {
		return adjuntos;
	}
	public void setAdjuntos(List<Adjuntos> adjuntos) {
		this.adjuntos = adjuntos;
	}
	@Override
	public String toString() {
		return "CasoConAdjuntos [id=" + id + ", solicita=" + solicita + ", atiende=" + atiende + ", categoria="
				+ categoria + ", severidad=" + severidad + ", descripcion=" + descripcion + ", estado=" + estado
				+ ", creacion=" + creacion + ", asunto=" + asunto + ", tipo=" + tipo + ", observaciones="
				+ observaciones + ", adjuntos=" + adjuntos + "]";
	}
	
}
