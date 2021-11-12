/**
 * 
 */
package com.sumset.techsupport.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sumset.techsupport.models.UsuariosModel;

/**
 * @author Agustín Palomino Pardo
 *
 */
@Repository
public interface UsuarioRepository extends CrudRepository<UsuariosModel, Long> {
	
}
