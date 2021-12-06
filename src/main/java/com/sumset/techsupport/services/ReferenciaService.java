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
	
	/**
	 * Método para buscar todas las referencias
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Referencia> obtenerTodasReferencias() throws Exception {
		return (ArrayList<Referencia>) referenciaRepo.findAll();
	}
	
	/**
	 * Método para crear y actualizar una referencia
	 * @param referencia
	 * @return
	 * @throws Exception
	 */
	public Referencia guardarReferencia(Referencia referencia) throws Exception {
		return referenciaRepo.save(referencia);
	}

	/**
	 * Método para buscar referencia por id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Optional<Referencia> obtenerReferenciaPorId(Long id) throws Exception {
		return referenciaRepo.findById(id);
	}
	
	/**
	 * Método para eliminar una referencia
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Boolean eliminarReferencia(Long id) throws Exception {
		try {
			referenciaRepo.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Método para buscar las referencias hijas de un padre cod
	 * @param cod
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Referencia> obtenerHijos(String cod) throws Exception {
		return referenciaRepo.findByRefRefCodigo(cod);
	}
	
	/**
	 * Método para buscar una referencia por código
	 * @param cod
	 * @return
	 * @throws Exception
	 */
	public Referencia obtenerRefPorCod(String cod) throws Exception {
		return referenciaRepo.findByRefCodigo(cod);
	}
	
	/**
	 * Método para obtener todas las referencias padre
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Referencia> buscarRefPadre() throws Exception {
		return referenciaRepo.buscarRefPadre();
	}
	
}
