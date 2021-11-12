/**
 * 
 */
package com.sumset.techsupport.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sumset.techsupport.models.EmpresaModel;
import com.sumset.techsupport.repositories.EmpresaRepository;

/**
 * @author Sumset
 *
 */
@Service
public class EmpresaService {
	
	@Autowired
	EmpresaRepository empresaRepo;
	
	public ArrayList<EmpresaModel> obtenerTodasEmpresas() throws Exception {
		return (ArrayList<EmpresaModel>) empresaRepo.findAll();
	}
	
	public EmpresaModel crearEmpresa(EmpresaModel empresa) throws Exception {
		return empresaRepo.save(empresa);
	}

	public Optional<EmpresaModel> obtenerEmpresaPorId(Long id) throws Exception {
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
	
	public EmpresaModel obtenerEmpresaPorNit(String nit) throws Exception {
		return empresaRepo.findByempNit(nit);
	}
	
}