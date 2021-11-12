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

import com.sumset.techsupport.models.CasosModel;
import com.sumset.techsupport.services.CasosService;

/**
 * @author Agustín Palomino Pardo
 *
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/caso")
public class CasosController {

	@Autowired
	CasosService casosService;
	
	@GetMapping()
	public ArrayList<CasosModel> obtenerCasos() throws Exception {
		return casosService.obtenerTodosCasos();
	}
	
	@PostMapping()
	public CasosModel guardarCaso(@RequestBody CasosModel caso) throws Exception {
		return casosService.guardarCaso(caso);
	}
	
	@GetMapping( path = "/{id}")
	public Optional<CasosModel> obtenerCasoPorId(@PathVariable("id") Long id) throws Exception {
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
