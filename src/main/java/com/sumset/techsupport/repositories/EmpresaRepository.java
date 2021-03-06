/**
 * 
 */
package com.sumset.techsupport.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sumset.techsupport.models.Empresa;

/**
 * @author Agustín Palomino Pardo
 *
 */
@Repository
public interface EmpresaRepository extends CrudRepository<Empresa, Long> {

	public abstract Empresa findByempNit(String nit);
	
	

}
