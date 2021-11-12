/**
 * 
 */
package com.sumset.techsupport.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sumset.techsupport.models.ReferenciaModel;
import com.sumset.techsupport.repositories.ReferenciaRepository;

/**
 * @author Agustín Palomino Pardo
 *
 */
@Service
public class ReferenciaService {
	
	@Autowired
	ReferenciaRepository referenciaRepo;
	
	public ArrayList<ReferenciaModel> obtenerTodasReferencias() throws Exception {
		return (ArrayList<ReferenciaModel>) referenciaRepo.findAll();
	}
	
	public ReferenciaModel guardarReferencia(ReferenciaModel referencia) throws Exception {
		return referenciaRepo.save(referencia);
	}

	public Optional<ReferenciaModel> obtenerReferenciaPorId(Long id) throws Exception {
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
}
