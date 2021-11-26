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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sumset.techsupport.models.Casos;
import com.sumset.techsupport.services.CasosService;

/**
 * @author Agustín Palomino Pardo
 *
 */
@RestController
@CrossOrigin("*")
//@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/home/caso")
public class CasosController {

	@Autowired
	CasosService casosService;
	
	@GetMapping()
	public ArrayList<Casos> obtenerCasos() throws Exception {
		return casosService.obtenerTodosCasos();
	}
	
	@PostMapping()
	public Casos guardarCaso(@RequestBody Casos caso) throws Exception {
		return casosService.guardarCaso(caso);
	}
	
	@GetMapping( path = "/{id}")
	public Optional<Casos> obtenerCasoPorId(@PathVariable("id") Long id) throws Exception {
		return casosService.obtenerCasoPorId(id);
	}
	
	@DeleteMapping( path = "/{id}")
	public String eliminarCaso(@PathVariable("id") Long id) throws Exception {
		boolean ok = this.casosService.eliminarCaso(id);
		if (ok) {
			return "Se eliminó el Caso con id: " + id;
		} else {
			return "No se pudo eliminar el Caso con id: " + id;
		}
	}
}
