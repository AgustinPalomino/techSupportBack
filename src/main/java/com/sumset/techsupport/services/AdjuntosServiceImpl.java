/**
 * 
 */
package com.sumset.techsupport.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Agustín Palomino Pardo
 *
 */
@Service
public class AdjuntosServiceImpl implements AdjuntosService {

	// Nombre de la carpeta donde vamos a almacenar los archivos
	// Se crea a nivel de raiz la carpeta
	private final Path root = Paths.get("logos");

	@Override
	public void init() {
		try {
			if (Files.notExists(root)) {
				Files.createDirectory(root);
			}
		} catch (IOException e) {
			throw new RuntimeException("No se puede iniciar el almacenamiento" + e);

		}
	}

	@Override
	public void save(MultipartFile file) {
		try {
			// copy (que queremos copiar, a donde queremos copiar)
			Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
		} catch (IOException e) {
			throw new RuntimeException("No se puede guardar el archivo");
		}

	}

	@Override
	public Resource load(String filename) {
		try {
			Path file = root.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("No se puede leer el archivo");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Error: " + e.getMessage());
		}
	}

	@Override
	public Stream<Path> loadAll() {
		// Files.walk recorre nuestras carpetas (uploads) buscando los archivos
		// el 1 es la profundidad o nivel que queremos recorrer
		// :: Referencias a metodos
		// Relativize sirve para crear una ruta relativa entre la ruta dada y esta ruta

		try {
			return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
		} catch (RuntimeException | IOException e) {
			throw new RuntimeException("No se pueden cargar los archivos");
		}
	}

	@Override
	public String deleteFile(String filename) {
		try {
			boolean borrar = Files.deleteIfExists(this.root.resolve(filename));
			return "Archivo borrado";
		} catch (IOException e) {
			e.printStackTrace();
			return "Error borrando archivo";
		}
	}

}
