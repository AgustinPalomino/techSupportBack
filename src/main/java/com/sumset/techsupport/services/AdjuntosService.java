/**
 * 
 */
package com.sumset.techsupport.services;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Agustín Palomino Pardo
 *
 */
public interface AdjuntosService {
	
	/*
    Metodo para crear la carpeta donde vamos a guardar los archivos
     */
    public void init();

    /*
    Metodo para guardar los archivos
     */
    public void save(MultipartFile file, Long empId);

    /*
    Metodo para cargar un archivo desde la carpeta
     */
    public Resource load(String empid, String filename);

    /*
    Metodo para Cargar todos los archivos desde la carpeta
     */
    public Stream<Path> loadAll(String empid);

    /*
    Metodo para Borrar un archivo
     */
    public String deleteFile(String filename, Long empId);

    /*
    Metodo para crear la carpeta donde vamos a guardar los archivos
     */
	void init(Long empId);

}
