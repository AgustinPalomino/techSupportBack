/**
 * 
 */
package com.sumset.techsupport.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.sumset.techsupport.message.FileMessage;
import com.sumset.techsupport.models.Adjuntos;
import com.sumset.techsupport.services.AdjuntosService;

/**
 * @author Agustín Palomino Pardo
 *
 */
@Controller
@CrossOrigin("*")
public class AdjuntosController {
	
	//Inyectamos el servicio
    @Autowired
    AdjuntosService adjuntosService;
    
    @PostMapping("home/upload")
    public ResponseEntity<FileMessage> uploadFiles(@RequestParam("files")MultipartFile[] files, @RequestParam("empId") String empId){
    	String message = "";
        adjuntosService.init(Long.parseLong(empId));
        try{
            List<String> fileNames = new ArrayList<>();

            Arrays.asList(files).stream().forEach(file->{
                adjuntosService.save(file, Long.parseLong(empId));
                fileNames.add(file.getOriginalFilename());
            });

            message = "Se subieron los archivos correctamente " + fileNames;
            return ResponseEntity.status(HttpStatus.OK).body(new FileMessage(message));
        }catch (Exception e){
            message = "Fallo al subir los archivos";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new FileMessage(message));
        }
    }

    //Método para obtener la lista de los archivos guardados
    //@GetMapping("home/files/{empId}")
    @RequestMapping(value = "home/files/{empid}", method = RequestMethod.POST)
    public ResponseEntity<List<Adjuntos>> getFiles(@PathVariable String empid) {
    	System.out.println("ENTRO A GETFILES");
    	List<Adjuntos> fileInfos = adjuntosService.loadAll(empid).map(path -> {
    		String fileName = path.getFileName().toString();
    		System.out.println("FileName: "+fileName);
    		String url = MvcUriComponentsBuilder.fromMethodName(AdjuntosController.class, 
    				"getFile", empid, path.getFileName().toString()).build().toString();
    		System.out.println("URL: "+url);
    		return new Adjuntos(fileName, url);
    	}).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }


    //@GetMapping("home/file/{empId}/{filename:.+}")
    @RequestMapping(value = "home/file/{empId}/{filename}", method = RequestMethod.POST)
    public ResponseEntity<Resource> getFile(@PathVariable String empid, @PathVariable String filename){
        Resource file = adjuntosService.load(empid, filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\""+file.getFilename() + "\"").body(file);
    }

    //@GetMapping("/delete/{filename:.+}")
    @RequestMapping(value = "home/delete/{empId}/{filename:.+}", method = RequestMethod.POST)
    public ResponseEntity<FileMessage> deleteFile(@PathVariable String filename, @PathVariable("empId") Long empId) {
        String message = "";
        try {
            message = adjuntosService.deleteFile(filename, empId);
            return ResponseEntity.status(HttpStatus.OK).body(new FileMessage(message));
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new FileMessage(message));
        }
    }

}
