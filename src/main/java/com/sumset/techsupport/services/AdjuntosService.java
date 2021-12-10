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
    Metodo para cargar un archivo
     */
    public Resource load(String filename);

    /*
    Metodo para Cargar todos los archivos
     */
    public Stream<Path> loadAll();

    /*
    Metodo para Borrar un archivo
     */
    public String deleteFile(String filename);

    /*
    Metodo para crear la carpeta donde vamos a guardar los archivos
     */
	void init(Long empId);

}
