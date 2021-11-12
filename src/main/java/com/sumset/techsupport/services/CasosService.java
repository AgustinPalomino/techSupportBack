/**
 * 
 */
package com.sumset.techsupport.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sumset.techsupport.models.CasosModel;
import com.sumset.techsupport.repositories.CasosRepository;

/**
 * @author Agustín Palomino Pardo
 *
 */
@Service
public class CasosService {
	
	@Autowired
	CasosRepository casosRepo;
	
	public ArrayList<CasosModel> obtenerTodosCasos()  throws Exception {
		return (ArrayList<CasosModel>) casosRepo.findAll();
	}
	
	public CasosModel guardarCaso(CasosModel caso) throws Exception {
		return casosRepo.save(caso);
	}
	
	public Optional<CasosModel> obtenerCasoPorId(Long id) throws Exception {
		return casosRepo.findById(id);
	}
	
	public Boolean eliminarCaso(Long id) throws Exception {
		try {
			casosRepo.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
