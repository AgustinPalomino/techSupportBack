/**
 * 
 */
package com.sumset.techsupport.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sumset.techsupport.models.UsuariosModel;
import com.sumset.techsupport.repositories.UsuarioRepository;

/**
 * @author Agustín Palomino Pardo
 *
 */
@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepo;
	
	public ArrayList<UsuariosModel> obtenerTodosUsuarios() throws Exception {
		return (ArrayList<UsuariosModel>) usuarioRepo.findAll();
	}
	
	public UsuariosModel guardarUsuario(UsuariosModel usuario) throws Exception {
		return usuarioRepo.save(usuario);
	}
	
	public Optional<UsuariosModel> obtenerUsuarioPorId(Long id) throws Exception {
		return usuarioRepo.findById(id);
	}
	
	public Boolean eliminarUsuario(Long id) throws Exception {
		try {
			usuarioRepo.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
