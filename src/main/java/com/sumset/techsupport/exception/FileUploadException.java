/**
 * 
 */
package com.sumset.techsupport.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.sumset.techsupport.message.FileMessage;

/**
 * @author Agustín Palomino Pardo
 *
 */
@ControllerAdvice
public class FileUploadException {
	
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<FileMessage> maxSizeException(MaxUploadSizeExceededException exc) {
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
				.body(new FileMessage("Uno o más archivos exceden el tamaño máximo"));
	}

}
