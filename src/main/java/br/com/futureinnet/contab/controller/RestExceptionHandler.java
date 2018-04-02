package br.com.futureinnet.contab.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.futureinnet.contab.exception.BusinessException;
import br.com.futureinnet.contab.model.ErrorDetail;

/**
 * Classe responsável por tratamento de erros 
 * em requisições Rest
 * 
 * 
 * @author Jane
 *
 */
@ControllerAdvice
public class RestExceptionHandler  extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleResourceNotFoundException(BusinessException ex,
            HttpServletRequest request) {

        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetail.setTitle("Recurso não encontrado.");
        errorDetail.setMessage(ex.getClass().getName() + " - " +  ex.getMessage()); 

        return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
    }
}
