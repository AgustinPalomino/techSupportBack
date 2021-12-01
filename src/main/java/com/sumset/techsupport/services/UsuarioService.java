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
	
	public ArrayList<Usuarios> obtenerTodosUsuarios() throws Exception {
		return (ArrayList<Usuarios>) usuarioRepo.findAll();
	}
	
	public Usuarios guardarUsuario(Usuarios usuario) throws Exception {
		return usuarioRepo.save(usuario);
	}
	
	public Optional<Usuarios> obtenerUsuarioPorId(Long id) throws Exception {
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
	
	public Usuarios obtenerUsuarioPorMail(String mail) throws Exception {
		return usuarioRepo.findByusrMail(mail);
	}
	
	public Usuarios autenticarUsuario(String userMail, String userPass) throws Exception {
		return usuarioRepo.autenticarUsuario(userMail, userPass);
	}

}
