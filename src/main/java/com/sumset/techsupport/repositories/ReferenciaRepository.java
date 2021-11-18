/**
 * 
 */
package com.sumset.techsupport.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sumset.techsupport.models.Referencia;

/**
 * @author Agustín Palomino Pardo
 *
 */
@Repository
public interface ReferenciaRepository extends CrudRepository<Referencia, Long> {
	
	/**
	 * Query que devuelve los hijos de un padre en Referencia
	 * @param refRefCodigo
	 * @return
	 */
	public abstract ArrayList<Referencia> findByRefRefCodigo(String refRefCodigo);
	
	/**
	 * Query para traer una referencia por código
	 * @param refCodigo
	 * @return
	 */
	public abstract Referencia findByRefCodigo(String refCodigo);

	/**
	 * Query para consultar las Referencias padre
	 * @return
	 */
	@Query(value = "select * from ts_referencia where ref_ref_codigo is null", nativeQuery = true)
	public ArrayList<Referencia> buscarRefPadre();
	
	
}
