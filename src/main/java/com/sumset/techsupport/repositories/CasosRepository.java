/**
 * 
 */
package com.sumset.techsupport.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sumset.techsupport.models.Casos;
import com.sumset.techsupport.models.CasosTablaDTO;

/**
 * @author Agustín Palomino Pardo
 *
 */
@Repository
public interface CasosRepository extends CrudRepository<Casos, Long> {

	/**
	 * Query para buscar los casos por usuario Id
	 * @param id
	 * @return
	 */
	//@Query(value = "select * from ts_casos where cas_usr_id = ?1", nativeQuery = true)
	@Query(value = "select cas_id, cas_subject, cas_fecha_inicio, ref_nombre"
			+ " from ts_casos, ts_referencia"
			+ " where cas_estado = ref_codigo"
			+ " and cas_usr_id = ?1", nativeQuery = true)
	public List<Object[]> buscarPorUsrId(Long id);
	
	/**
	 * Query para buscar los casos asignados a un funcionario
	 * @param id
	 * @return
	 */
	@Query(value = "select * from ts_casos where cas_atiende_usr_id = ?1", nativeQuery = true)
	public abstract ArrayList<Casos> buscarCasosAsignadosAUsr(Long id);
	
}
