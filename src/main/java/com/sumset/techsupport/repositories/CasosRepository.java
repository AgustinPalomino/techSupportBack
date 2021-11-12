/**
 * 
 */
package com.sumset.techsupport.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sumset.techsupport.models.CasosModel;

/**
 * @author Agustín Palomino Pardo
 *
 */
@Repository
public interface CasosRepository extends CrudRepository<CasosModel, Long> {

}
