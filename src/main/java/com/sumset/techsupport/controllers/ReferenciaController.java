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

import com.sumset.techsupport.models.ReferenciaModel;
import com.sumset.techsupport.services.ReferenciaService;

/**
 * @author Agustín Palomino Pardo
 *
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/ref")
public class ReferenciaController {
	
	@Autowired
	ReferenciaService referenciaService;
	
	@GetMapping()
	public ArrayList<ReferenciaModel> obtenerTodasReferencias() throws Exception {
		return referenciaService.obtenerTodasReferencias();
	}
	
	@PostMapping()
	public ReferenciaModel guardarReferencia(@RequestBody ReferenciaModel referencia) throws Exception {
		return this.referenciaService.guardarReferencia(referencia);
	}
	
	@GetMapping( path = "/{id}")
	public Optional<ReferenciaModel> obtenerReferenciaPorId(@PathVariable("id") Long id) throws Exception {
		return this.referenciaService.obtenerReferenciaPorId(id);
	}
	
	@DeleteMapping( path = "/{id}")
	public String eliminarReferencia(@PathVariable("id") Long id) throws Exception {
		boolean ok = this.referenciaService.eliminarReferencia(id);
		if (ok) {
			return "Se eliminó la Referencia con id: " + id;
		} else {
			return "No se pudo eliminar la Referencia con id: " + id;
		}
	}
	
	

}
