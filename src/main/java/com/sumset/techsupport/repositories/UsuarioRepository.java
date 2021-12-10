/**
 * 
 */
package com.sumset.techsupport.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
	
	/**
	 * Query para verificar mail y contraseña de usuario
	 * @param userMail
	 * @param userPass
	 * @return
	 */
	@Query("select u from Usuarios u where u.usrMail = ?1 and u.usrClave = ?2")
	public Usuarios autenticarUsuario(String userMail, String userPass);
	
	/**
	 * Query para listar todos los técnicos
	 * @return
	 */
	@Query("select u from Usuarios u where u.usrRol like 'R03' ")
	public ArrayList<Usuarios> buscarTecnicos();
}
