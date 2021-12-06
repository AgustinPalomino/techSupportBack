/**
 * 
 */
package com.sumset.techsupport.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sumset.techsupport.models.Empresa;
import com.sumset.techsupport.models.Usuarios;
import com.sumset.techsupport.repositories.UsuarioRepository;

/**
 * @author Agustín Palomino Pardo
 *
 */
@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepo;
	
	/**
	 * Método para obtener todos los usuarios
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Usuarios> obtenerTodosUsuarios() throws Exception {
		return (ArrayList<Usuarios>) usuarioRepo.findAll();
	}
	
	/**
	 * Método para crear y actualizar usuarios
	 * @param usuario
	 * @return
	 * @throws Exception
	 */
	public Usuarios guardarUsuario(Usuarios usuario) throws Exception {
		return usuarioRepo.save(usuario);
	}
	
	/**
	 * Método para obtener usuario por id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Optional<Usuarios> obtenerUsuarioPorId(Long id) throws Exception {
		return usuarioRepo.findById(id);
	}
	
	/**
	 * Método para eliminar usuario
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Boolean eliminarUsuario(Long id) throws Exception {
		try {
			usuarioRepo.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Método para buscar usuario por mail
	 * @param mail
	 * @return
	 * @throws Exception
	 */
	public Usuarios obtenerUsuarioPorMail(String mail) throws Exception {
		return usuarioRepo.findByusrMail(mail);
	}
	
	/**
	 * Método para buscar usuario por mail y password
	 * @param userMail
	 * @param userPass
	 * @return
	 * @throws Exception
	 */
	public Usuarios autenticarUsuario(String userMail, String userPass) throws Exception {
		return usuarioRepo.autenticarUsuario(userMail, userPass);
	}

}
