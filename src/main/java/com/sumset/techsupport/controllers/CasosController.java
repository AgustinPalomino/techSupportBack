/**
 * 
 */
package com.sumset.techsupport.controllers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.LongPredicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
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

import com.sumset.techsupport.models.Adjuntos;
import com.sumset.techsupport.models.CasoConAdjuntos;
import com.sumset.techsupport.models.Casos;
import com.sumset.techsupport.models.CasosTablaDTO;
import com.sumset.techsupport.services.AdjuntosService;
import com.sumset.techsupport.services.CasosService;

/**
 * @author Agustín Palomino Pardo
 *
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/home/casos")
public class CasosController {

	@Autowired
	CasosService casosService;
	@Autowired
	AdjuntosService adjuntosService;
	
	@RequestMapping(value = "obtenertodos", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Casos>> obtenerCasos() throws Exception {
		ArrayList<Casos> casos = casosService.obtenerTodosCasos();
		return ResponseEntity.status(200).body(casos);
	}
	
	@RequestMapping(value = "crear", method = RequestMethod.POST)
	public ResponseEntity<Casos> crearCaso(@RequestBody Casos caso) throws Exception {
		System.out.println(caso);
		Casos casoCreado = casosService.guardarCaso(caso);
		return ResponseEntity.status(200).body(casoCreado);
	}
	
	@RequestMapping(value = "porid/{id}", method = RequestMethod.POST)
	public ResponseEntity<Casos> obtenerCasoPorId(@PathVariable("id") Long id) throws Exception {
		Optional<Casos> caso = casosService.obtenerCasoPorId(id);
		if (caso.isPresent()) {
			return ResponseEntity.ok(caso.get());
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@RequestMapping(value = "poridAdj/{id}", method = RequestMethod.POST)
	public ResponseEntity<CasoConAdjuntos> obtenerCaspoPorIdAdjuntos(@PathVariable("id") Long id) throws Exception {
		System.out.println("ID que llega: "+id);
		Optional<Casos> caso = casosService.obtenerCasoPorId(id);
		System.out.println("Caso obtenido de la consulta: "+caso.toString());
		CasoConAdjuntos casoCon = new CasoConAdjuntos();
		List<Adjuntos> lstAdjuntos = new ArrayList<>();
		if (!caso.isEmpty()) {	
			casoCon.setId(caso.get().getId());
			casoCon.setCreacion(caso.get().getCasFechaIni());
			casoCon.setTipo(caso.get().getCasTipo());
			casoCon.setCategoria(caso.get().getCasCategoria());
			casoCon.setSeveridad(caso.get().getCasSeveridad());
			casoCon.setAsunto(caso.get().getCasSubject());
			casoCon.setDescripcion(caso.get().getCasDescripcion());
			casoCon.setSolicita(caso.get().getUsuario().getId());
			
			for (String adj : caso.get().getCasAdjuntos()) {
				Adjuntos adjunto = new Adjuntos();
				adjunto.setName(adj);
				String empid = caso.get().getUsuario().getEmpresa().getId().toString();
				//Resource url = adjuntosService.load(empid, adj);
				adjunto.setUrl(adjuntosService.load(empid, adj));
				//adjunto.setUrl(url.toString());
				lstAdjuntos.add(adjunto);
			}
			casoCon.setAdjuntos(lstAdjuntos);
			return ResponseEntity.ok(casoCon);
		}
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "borrar/{id}", method = RequestMethod.POST)
	public String eliminarCaso(@PathVariable("id") Long id) throws Exception {
		boolean ok = this.casosService.eliminarCaso(id);
		if (ok) {
			return "Se eliminó el Caso con id: " + id;
		} else {
			return "No se pudo eliminar el Caso con id: " + id;
		}
	}
	
	@RequestMapping(value = "buscarporusr/{id}", method = RequestMethod.POST)
	public ResponseEntity<ArrayList<CasosTablaDTO>> buscarPorUsrId(@PathVariable("id") Long id) throws Exception {
		
		List<Object[]> lstObjects = casosService.obtenerCasosPorUsrId(id);
		List<CasosTablaDTO> lstTablaDTO = new ArrayList<>();
		for (Object[] objetos : lstObjects) {
			CasosTablaDTO casos = new CasosTablaDTO();
			casos.setId(Long.parseLong(objetos[0].toString()));
			casos.setAsunto(objetos[1].toString());
			casos.setCreacion(Date.valueOf(objetos[2].toString()));
			casos.setEstado(objetos[3].toString());
			lstTablaDTO.add(casos);
		}
		
		if (lstTablaDTO != null && lstTablaDTO.size() > 0) {
			return ResponseEntity.ok(new ArrayList<>(lstTablaDTO));
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@RequestMapping(value = "buscarporfun/{id}", method = RequestMethod.POST)
	public ResponseEntity<ArrayList<Casos>> buscarCasosAsignadosAUsr(@PathVariable("id") Long id) throws Exception {
		ArrayList<Casos> casos = casosService.buscarCasosAsignadosAUsr(id);
		if (casos != null) {
			return ResponseEntity.ok(casos);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@RequestMapping(value = "buscarnoasinados", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Casos>> buscarCasosSinAsignar() throws Exception {
		ArrayList<Casos> casos = casosService.buscarCasosSinAsignar();
		if (casos != null) {
			return ResponseEntity.ok(casos);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@RequestMapping(value = "pendientes", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Casos>> buscarCasosPendientes() throws Exception {
		ArrayList<Casos> casos = casosService.buscarCasosPendientes();
		if (casos != null) {
			return ResponseEntity.ok(casos);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@RequestMapping(value = "pendientestecnico", method = RequestMethod.POST)
	public ResponseEntity<ArrayList<Casos>> buscarCasosPendientesPorTecnico(@PathVariable("id") Long id) throws Exception {
		ArrayList<Casos> casos = casosService.buscarCasosPendientesPorTecnico(id);
		if (casos != null) {
			return ResponseEntity.ok(casos);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
}
