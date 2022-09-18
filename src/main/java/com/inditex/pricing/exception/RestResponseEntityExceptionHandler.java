package com.inditex.pricing.exception;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.inditex.pricing.dto.response.APIErrorResponse;
import com.inditex.pricing.util.MsInfoUtil;


@ControllerAdvice
public class RestResponseEntityExceptionHandler {

	protected final Logger log = LoggerFactory.getLogger(getClass());

	
	@ExceptionHandler(PriceNotFoundException.class)
	protected ResponseEntity<Object> handlePriceNotFoundException(PriceNotFoundException ex, WebRequest request) {
		return ResponseEntity.noContent().build();
	}
	
	@ExceptionHandler(BindException.class)
	protected ResponseEntity<Object> handleBindException(BindException ex, WebRequest request) {

		log.info("handleBindException() - validationErrorMsg: {}", ex.getMessage());

		APIErrorResponse apiError = APIErrorResponse.builder()
				.status(MsInfoUtil.buildStatusString(HttpStatus.BAD_REQUEST))
				.message(ex.getErrorCount() + " field with errors")
				.errors(MsInfoUtil.buildFieldErrorMessage(ex))
				.timestamp(MsInfoUtil.getActualDateTime())
				.path(MsInfoUtil.getPath(request))
				.build();

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		
	}

	@ExceptionHandler(value = { java.lang.RuntimeException.class })
	protected ResponseEntity<Object> handleHttpRuntimeException(RuntimeException ex, WebRequest request) {

		log.info("handleHttpRuntimeException: {0}", ex);

		APIErrorResponse apiError = APIErrorResponse.builder()
				.status(MsInfoUtil.buildStatusString(HttpStatus.INTERNAL_SERVER_ERROR))
				.message("Internal error")
				.errors(Arrays.asList("Se ha producido un error, intente nuevamente en unos minutos"))
				.timestamp(MsInfoUtil.getActualDateTime())
				.path(MsInfoUtil.getPath(request))
				.build();

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);

	}
}
