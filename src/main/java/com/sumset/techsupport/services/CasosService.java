/**
 * 
 */
package com.sumset.techsupport.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sumset.techsupport.models.Casos;
import com.sumset.techsupport.models.CasosTablaDTO;
import com.sumset.techsupport.repositories.CasosRepository;

/**
 * @author Agustín Palomino Pardo
 *
 */
@Service
public class CasosService {
	
	@Autowired
	CasosRepository casosRepo;
	
	/**
	 * Método para buscar todos los casos
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Casos> obtenerTodosCasos()  throws Exception {
		return (ArrayList<Casos>) casosRepo.findAll();
	}
	
	/**
	 * Método para crear y acutalizar un caso
	 * @param caso
	 * @return
	 * @throws Exception
	 */
	public Casos guardarCaso(Casos caso) throws Exception {
		return casosRepo.save(caso);
	}
	
	/**
	 * Método para buscar caso por id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Optional<Casos> obtenerCasoPorId(Long id) throws Exception {
		return casosRepo.findById(id);
	}
	
	/**
	 * Método para eliminar un caso por id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Boolean eliminarCaso(Long id) throws Exception {
		try {
			casosRepo.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Método para obtener los casos por cliente id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<Object[]> obtenerCasosPorUsrId(Long id) throws Exception {
		return (List<Object[]>) casosRepo.buscarPorUsrId(id);
	}
	
	/**
	 * Método para buscar los casos asignados a un funcionario
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Casos> buscarCasosAsignadosAUsr(Long id) throws Exception {
		return (ArrayList<Casos>) casosRepo.buscarCasosAsignadosAUsr(id);
	}
	
	/**
	 * Método para buscar loas casos que no han sido asignados
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Casos> buscarCasosSinAsignar() throws Exception {
		return (ArrayList<Casos>) casosRepo.buscarCasosSinAsignar();
	}
	
	/**
	 * Método para buscar los casos pendientes
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Casos> buscarCasosPendientes() throws Exception {
		return (ArrayList<Casos>) casosRepo.buscarCasosPendientes();
	}
	
	/**
	 * Método para buscar los casoso pendientes por cada técnico
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Casos> buscarCasosPendientesPorTecnico(Long id) throws Exception {
		return (ArrayList<Casos>) casosRepo.buscarCasosPendientesPorTecnico(id);
	}

}
