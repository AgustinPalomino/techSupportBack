/**
 * 
 */
package com.sumset.techsupport.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * @author Agustín Palomino Pardo
 *
 */
@Entity
@Table(name = "ts_usuarios")
public class Usuarios {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usr_id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "usr_nombre", nullable = false, length = 50)
	private String usrNombre;
	
	@Column(name = "usr_alias", nullable = false, length = 15)
	private String usrAlias;
	
	@Column(name = "usr_clave", nullable = false, length = 20)
	private String usrClave;
	
	@Column(name = "usr_mail", unique = true, nullable = false, length = 50)
	private String usrMail;
	
	@Column(name = "usr_rol", nullable = false, length = 4)
	private String usrRol;
	
	@JoinColumn(name = "usr_emp_id", referencedColumnName = "emp_id", nullable = false)
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	private Empresa empresa;
	
	public Usuarios() {
		super();
	}

	public Usuarios(Long id, String usrNombre, String usrAlias, String usrClave, String usrMail, String usrRol) {
		super();
		this.id = id;
		this.usrNombre = usrNombre;
		this.usrAlias = usrAlias;
		this.usrClave = usrClave;
		this.usrMail = usrMail;
		this.usrRol = usrRol;
	}


	//** Getters y setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsrNombre() {
		return usrNombre;
	}

	public void setUsrNombre(String usrNombre) {
		this.usrNombre = usrNombre;
	}

	public String getUsrAlias() {
		return usrAlias;
	}

	public void setUsrAlias(String usrAlias) {
		this.usrAlias = usrAlias;
	}

	public String getUsrClave() {
		return usrClave;
	}

	public void setUsrClave(String usrClave) {
		this.usrClave = usrClave;
	}

	public String getUsrMail() {
		return usrMail;
	}

	public void setUsrMail(String usrMail) {
		this.usrMail = usrMail;
	}

	public String getUsrRol() {
		return usrRol;
	}

	public void setUsrRol(String usrRol) {
		this.usrRol = usrRol;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@Override
	public String toString() {
		return "Usuarios [id=" + id + ", usrNombre=" + usrNombre + ", usrAlias=" + usrAlias + ", usrClave=" + usrClave
				+ ", usrMail=" + usrMail + ", usrRol=" + usrRol + ", empresa=" + empresa + "]";
	}

}
