/**
 * 
 */
package com.sumset.techsupport.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sumset.techsupport.models.EmpresaModel;

/**
 * @author Agustín Palomino Pardo
 *
 */
@Repository
public interface EmpresaRepository extends CrudRepository<EmpresaModel, Long> {

	public abstract EmpresaModel findByempNit(String nit);
	
	

}
