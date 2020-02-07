package com.library.controlleradvice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.library.model.LibraryResponse;

@RestControllerAdvice
public class LibraryControllerAdvice {

	/**
	 * To catch any exception from controller class and to format the response
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = Exception.class)
	public LibraryResponse<String> exceptionHandler(Exception e) {
		return new LibraryResponse<>(e.getMessage(), "FAILED");
	}
}
