package com.inditex.pricing.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 
 * Clase con metodos utiles para el microservicio
 *
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MsInfoUtil {

	/**
	 * Construye un mensaje para el Status Code de Http, concatenando
	 * el codigo con su descripcion.
	 * @param status
	 * @return un status formateado
	 */
	public static String buildStatusString(HttpStatus status) {
		return status.value() + " - " + status.getReasonPhrase();
	}

	/**
	 * Devuelve la fecha y hora actual, dandole un formato especifico.
	 * @return fecha en formato string.
	 */
	public static String getActualDateTime() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss");
		return LocalDateTime.now().format(formatter);
	}

	/**
	 * Nos permite obtener el path de un request dado.
	 * @param request
	 * @return un path
	 */
	public static String getPath(WebRequest request) {
		return ((ServletWebRequest) request).getDescription(false).replace("uri=", "");
	}

	/**
	 * Devuelve la lista de fields que tienen error ante un BindException
	 * @param ex
	 * @return lista de fields
	 */
	public static List<String> buildFieldErrorMessage(BindException ex) {
		return ex.getFieldErrors().stream().map(FieldError::getField).toList();
	}
	
}
