/**
 * 
 */
package com.sumset.techsupport.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sumset.techsupport.models.Empresa;
import com.sumset.techsupport.models.Usuarios;

/**
 * @author Agustín Palomino Pardo
 *
 */
@Repository
public interface UsuarioRepository extends CrudRepository<Usuarios, Long> {
	public abstract Usuarios findByusrMail(String mail);
}
