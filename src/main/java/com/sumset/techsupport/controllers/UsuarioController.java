/**
 * 
 */
package com.sumset.techsupport.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sumset.techsupport.models.UsuariosModel;
import com.sumset.techsupport.services.UsuarioService;

/**
 * @author Agustín Palomino Pardo
 *
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping()
	public ArrayList<UsuariosModel> obtenerUsuarios() throws Exception {
		return usuarioService.obtenerTodosUsuarios();
	}
	
	@PostMapping()
	public UsuariosModel guardarUsuario(@RequestBody UsuariosModel usuario) throws Exception {
		System.out.println(usuario.toString());
		return this.usuarioService.guardarUsuario(usuario);
	}

	@GetMapping( path = "/{id}")
	public Optional<UsuariosModel> obtenerUsuarioPorId(@PathVariable("id") Long id) throws Exception {
		return this.usuarioService.obtenerUsuarioPorId(id);
	}
	
	@DeleteMapping( path = "/{id}")
	public String eliminarUsuario(@PathVariable("id") Long id) throws Exception {
		boolean ok = this.usuarioService.eliminarUsuario(id);
		if (ok) {
			return "Se eliminó el usuario con id: " + id;
		} else {
			return "No se pudo eliminar el usuario con id: " + id;
		}
	}
	
}
