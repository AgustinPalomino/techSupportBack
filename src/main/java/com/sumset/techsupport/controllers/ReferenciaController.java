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
import com.sumset.techsupport.models.Referencia;
import com.sumset.techsupport.services.ReferenciaService;

/**
 * @author Agustín Palomino Pardo
 *
 */
@RestController
@CrossOrigin("*")
//@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/home/ref")
public class ReferenciaController {
	
	@Autowired
	ReferenciaService referenciaService;
	
	@RequestMapping(value = "obtenertodas", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Referencia>> obtenerTodasReferencias() throws Exception {
		ArrayList<Referencia> ref = referenciaService.obtenerTodasReferencias();
		return ResponseEntity.ok(ref);
	}
	
	@RequestMapping(value = "guardar", method = RequestMethod.POST)
	public ResponseEntity<Referencia> guardarReferencia(@RequestBody Referencia referencia) throws Exception {
		Referencia ref = referenciaService.guardarReferencia(referencia);
		return ResponseEntity.ok(ref);
	}
	
	@GetMapping( path = "refporid/{id}")
	public Optional<Referencia> obtenerReferenciaPorId(@PathVariable("id") Long id) throws Exception {
		return this.referenciaService.obtenerReferenciaPorId(id);
	}
	
	@RequestMapping(value = "borrar/{id}", method = RequestMethod.POST)
	public String eliminarReferencia(@PathVariable("id") Long id) throws Exception {
		boolean ok = this.referenciaService.eliminarReferencia(id);
		if (ok) {
			return "Se eliminó la Referencia con id: " + id;
		} else {
			return "No se pudo eliminar la Referencia con id: " + id;
		}
	}
	
	@RequestMapping(value = "refporcod/{cod}", method = RequestMethod.POST)
	public ResponseEntity<ArrayList<Referencia>> obtenerHijos(@PathVariable("cod") String cod) throws Exception {
		ArrayList<Referencia> ref = referenciaService.obtenerHijos(cod);
		if (!ref.isEmpty()) {
			return ResponseEntity.ok(ref);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@RequestMapping(value = "refcod/{cod}", method = RequestMethod.POST)
	public ResponseEntity<Referencia> obtenerRefPorCod(@PathVariable("cod") String cod) throws Exception {
		Referencia ref = referenciaService.obtenerRefPorCod(cod);
		if (ref != null) {
			return ResponseEntity.ok(ref);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@RequestMapping(value = "refpad", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Referencia>> buscarRefPadre() throws Exception {
		ArrayList<Referencia> ref = referenciaService.buscarRefPadre();
		if (!ref.isEmpty()) {
			return ResponseEntity.ok(ref);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

}
