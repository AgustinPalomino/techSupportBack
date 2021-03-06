/**
 * 
 */
package com.sumset.techsupport.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Agustín Palomino Pardo
 *
 */
@Entity
@Table(name = "ts_empresa")
public class Empresa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "emp_id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "emp_nombre", nullable = false, length = 100)
	private String empNombre;
	
	@Column(name = "emp_nit", unique = true, nullable = false, length = 20)
	private String empNit;
	
	@Column(name = "emp_telefono", length = 20)
	private String empTelefono;
	
	@Column(name = "emp_logo")
	//private byte[] empLogo;
	private String empLogo;
	
	public Empresa() {
		super();
	}
	
	public Empresa(Long id, String empNombre, String empNit, String empTelefono) {
		super();
		this.id = id;
		this.empNombre = empNombre;
		this.empNit = empNit;
		this.empTelefono = empTelefono;
	}

	//** Getters y setters
	public Long getId() {
		return id;
	}

	public String getEmpNombre() {
		return empNombre;
	}

	public void setEmpNombre(String empNombre) {
		this.empNombre = empNombre;
	}

	public String getEmpNit() {
		return empNit;
	}

	public void setEmpNit(String empNit) {
		this.empNit = empNit;
	}

//	@Lob
//    @Type(type = "org.hibernate.type.BinaryType")
//	@Column(length = 1200000)
//	public byte[] getEmpLogo() {
//		return empLogo;
//	}
//
//	public void setEmpLogo(byte[] empLogo) {
//		this.empLogo = empLogo;
//	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getEmpLogo() {
		return empLogo;
	}

	public void setEmpLogo(String empLogo) {
		this.empLogo = empLogo;
	}

	public String getEmpTelefono() {
		return empTelefono;
	}

	public void setEmpTelefono(String empTelefono) {
		this.empTelefono = empTelefono;
	}
	
	@Override
	public String toString() {
		return "Empresa [id=" + id + ", empNombre=" + empNombre + ", empNit=" + empNit + ", empTelefono=" + empTelefono
				+ ", empLogo=" + empLogo + "]";
	}

//	@Override
//	public String toString() {
//		return "Empresa [id=" + id + ", empNombre=" + empNombre + ", empNit=" + empNit + ", empTelefono=" + empTelefono
//				+ ", empLogo=" + Arrays.toString(empLogo) + "]";
//	}
	
}
