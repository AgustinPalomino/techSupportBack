/**
 * 
 */
package com.sumset.techsupport.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sumset.techsupport.models.Empresa;
import com.sumset.techsupport.models.Usuarios;
import com.sumset.techsupport.services.EmpresaService;

/**
 * @author Agustín Palomino Pardo
 *
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/home/empresa")
public class EmpresaController {
	
	@Autowired
	EmpresaService empresaService;
	
	@RequestMapping(value = "obtenertodas", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Empresa>> obtenerEmpresas() throws Exception {
		ArrayList<Empresa> empresas = empresaService.obtenerTodasEmpresas();
		return ResponseEntity.status(200).body(empresas);
	}
	
	@RequestMapping(value = "crear", method = RequestMethod.POST)
	public ResponseEntity<Empresa> crearEmpresa(@RequestBody Empresa empresa) throws Exception {
		Empresa empresaCreada = empresaService.crearEmpresa(empresa);
		return ResponseEntity.status(200).body(empresaCreada);
	}
	
	//@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "porid/{id}", method = RequestMethod.POST)
	public ResponseEntity<Empresa> obtenerEmpresaPorId(@PathVariable("id") Long id) throws Exception {
		Optional<Empresa> empresa = empresaService.obtenerEmpresaPorId(id);
		if (empresa.isPresent()) {
			return ResponseEntity.ok(empresa.get());
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@RequestMapping(value = "borrar/{id}", method = RequestMethod.POST)
	public String eliminarEmpresa(@PathVariable("id") Long id) throws Exception {
		boolean ok = this.empresaService.eliminarEmpresa(id);
		if (ok) {
			return "Se eliminó la Empresa con id: " + id;
		} else {
			return "No se pudo eliminar la Empresa con id: " + id;
		}
	}
	
	@RequestMapping(value = "buscarnit/{nit}", method = RequestMethod.POST)
	public ResponseEntity<Empresa> obtenerEmpresaPorNit(@PathVariable("nit") String nit) throws Exception {
		Empresa empresa = empresaService.obtenerEmpresaPorNit(nit);
		if (empresa != null) {
			return ResponseEntity.ok(empresa);
		}
		return ResponseEntity.noContent().build();
	}
	
}
