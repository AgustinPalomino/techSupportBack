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
	
	/**
	 * Método para buscar todas las empresas
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Empresa> obtenerTodasEmpresas() throws Exception {
		return (ArrayList<Empresa>) empresaRepo.findAll();
	}
	
	/**
	 * Método para crear y actualizar una empresa
	 * @param empresa
	 * @return
	 * @throws Exception
	 */
	public Empresa crearEmpresa(Empresa empresa) throws Exception {
		return empresaRepo.save(empresa);
	}

	/**
	 * Método para obtener una empresa por id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Optional<Empresa> obtenerEmpresaPorId(Long id) throws Exception {
		return empresaRepo.findById(id);
	}
	
	/**
	 * Método para eliminar una empresa
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Boolean eliminarEmpresa(Long id) throws Exception {
		try {
			empresaRepo.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Método para buscar una empres apor nit
	 * @param nit
	 * @return
	 * @throws Exception
	 */
	public Empresa obtenerEmpresaPorNit(String nit) throws Exception {
		return empresaRepo.findByempNit(nit);
	}
	
}
