/**
 * 
 */
package com.sumset.techsupport.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sumset.techsupport.models.Empresa;
import com.sumset.techsupport.repositories.EmpresaRepository;

/**
 * @author Agustín Palomino Pardo
 *
 */
@Service
public class EmpresaService {
	
	@Autowired
	EmpresaRepository empresaRepo;
	
	public ArrayList<Empresa> obtenerTodasEmpresas() throws Exception {
		return (ArrayList<Empresa>) empresaRepo.findAll();
	}
	
	public Empresa crearEmpresa(Empresa empresa) throws Exception {
		return empresaRepo.save(empresa);
	}

	public Optional<Empresa> obtenerEmpresaPorId(Long id) throws Exception {
		return empresaRepo.findById(id);
	}
	
	public Boolean eliminarEmpresa(Long id) throws Exception {
		try {
			empresaRepo.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public Empresa obtenerEmpresaPorNit(String nit) throws Exception {
		return empresaRepo.findByempNit(nit);
	}
	
}
