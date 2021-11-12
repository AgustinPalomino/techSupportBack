/**
 * 
 */
package com.sumset.techsupport.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sumset.techsupport.models.ReferenciaModel;

/**
 * @author Agustín Palomino Pardo
 *
 */
@Repository
public interface ReferenciaRepository extends CrudRepository<ReferenciaModel, Long> {
	
}
