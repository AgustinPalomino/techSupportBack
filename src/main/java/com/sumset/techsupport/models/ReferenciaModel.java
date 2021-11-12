/**
 * 
 */
package com.sumset.techsupport.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Agustín Palomino Pardo
 *
 */
@Entity
@Table(name = "ts_referencia")
public class ReferenciaModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ref_id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "ref_codigo", nullable = false, length = 4)
	private String refCodigo;
	
	@Column(name = "ref_nombre", nullable = false, length = 50)
	private String refNombre;
	
	@Column(name = "ref_descripcion")
	private String refDescripcion;
	
	@Column(name = "ref_valor")
	private String refValor;
	
	@Column(name = "ref_ref_codigo")
	private String refRefCodigo;

	public Long getId() {
		return id;
	}

	//**Getter y setters
	public void setId(Long id) {
		this.id = id;
	}

	public String getRefCodigo() {
		return refCodigo;
	}

	public void setRefCodigo(String refCodigo) {
		this.refCodigo = refCodigo;
	}

	public String getRefNombre() {
		return refNombre;
	}

	public void setRefNombre(String refNombre) {
		this.refNombre = refNombre;
	}

	public String getRefDescripcion() {
		return refDescripcion;
	}

	public void setRefDescripcion(String refDescripcion) {
		this.refDescripcion = refDescripcion;
	}

	public String getRefValor() {
		return refValor;
	}

	public void setRefValor(String refValor) {
		this.refValor = refValor;
	}

	public String getRefRefCodigo() {
		return refRefCodigo;
	}

	public void setRefRefCodigo(String refRefCodigo) {
		this.refRefCodigo = refRefCodigo;
	}

	@Override
	public String toString() {
		return "ReferenciaModel [id=" + id + ", refCodigo=" + refCodigo + ", refNombre=" + refNombre
				+ ", refDescripcion=" + refDescripcion + ", refValor=" + refValor + ", refRefCodigo=" + refRefCodigo
				+ "]";
	}

}
