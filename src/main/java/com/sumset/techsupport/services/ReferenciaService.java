/**
 * 
 */
package com.sumset.techsupport.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sumset.techsupport.models.Referencia;
import com.sumset.techsupport.repositories.ReferenciaRepository;

/**
 * @author Agustín Palomino Pardo
 *
 */
@Service
public class ReferenciaService {
	
	@Autowired
	ReferenciaRepository referenciaRepo;
	
	public ArrayList<Referencia> obtenerTodasReferencias() throws Exception {
		return (ArrayList<Referencia>) referenciaRepo.findAll();
	}
	
	public Referencia guardarReferencia(Referencia referencia) throws Exception {
		return referenciaRepo.save(referencia);
	}

	public Optional<Referencia> obtenerReferenciaPorId(Long id) throws Exception {
		return referenciaRepo.findById(id);
	}
	
	public Boolean eliminarReferencia(Long id) throws Exception {
		try {
			referenciaRepo.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public ArrayList<Referencia> obtenerReferenciaPorCodigo(String cod) throws Exception {
		return referenciaRepo.findByRefRefCodigo(cod);
	}
	
	public Referencia obtenerRefPorCod(String cod) throws Exception {
		return referenciaRepo.findByRefCodigo(cod);
	}
	
	public ArrayList<Referencia> buscarRefPadre() throws Exception {
		return referenciaRepo.buscarRefPadre();
	}
	
}
