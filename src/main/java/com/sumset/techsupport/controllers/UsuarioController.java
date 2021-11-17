/**
 * 
 */
package com.sumset.techsupport.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sumset.techsupport.models.Empresa;
import com.sumset.techsupport.models.Usuarios;
import com.sumset.techsupport.services.UsuarioService;

/**
 * @author Agustín Palomino Pardo
 *
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/home/usuario")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@RequestMapping(value = "obtenertodos", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Usuarios>> obtenerUsuarios() throws Exception {
		ArrayList<Usuarios> usuarios = usuarioService.obtenerTodosUsuarios();
		return ResponseEntity.ok(usuarios);
	}
	
	@RequestMapping(value = "guardar", method = RequestMethod.POST)
	public ResponseEntity<Usuarios> guardarUsuario(@RequestBody Usuarios usuario) throws Exception {
		Usuarios usr = usuarioService.guardarUsuario(usuario);
		return ResponseEntity.ok(usr);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.POST)
	public ResponseEntity<Usuarios> obtenerUsuarioPorId(@PathVariable("id") Long id) throws Exception {
		Optional<Usuarios> usr = usuarioService.obtenerUsuarioPorId(id);
		if (usr.isPresent()) {
			return ResponseEntity.ok(usr.get());
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@RequestMapping(value = "borrar/{id}", method = RequestMethod.POST)
	public String eliminarUsuario(@PathVariable("id") Long id) throws Exception {
		boolean ok = this.usuarioService.eliminarUsuario(id);
		if (ok) {
			return "Se eliminó el usuario con id: " + id;
		} else {
			return "No se pudo eliminar el usuario con id: " + id;
		}
	}
	
	@RequestMapping(value = "buscarpormail/{mail}", method = RequestMethod.POST)
	public ResponseEntity<Usuarios> obtenerUsuarioPorMail(@PathVariable("mail") String mail) throws Exception {
		Usuarios usr = usuarioService.obtenerUsuarioPorMail(mail);
		if (usr != null) {
			return ResponseEntity.ok(usr);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
}
