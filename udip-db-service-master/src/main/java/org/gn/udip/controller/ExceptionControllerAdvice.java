package org.gn.udip.controller;

import org.apache.log4j.Logger;
import org.gn.udip.exception.DBException;
import org.gn.udip.exception.MailerException;
import org.gn.udip.exception.ValidationException;
import org.gn.udip.model.RequestStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ExceptionControllerAdvice {
	
	private static Logger logger=Logger.getLogger(ExceptionControllerAdvice.class);
	
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<RequestStatus> exceptionHandler(MissingServletRequestParameterException e){
    	logger.error(e);
     	RequestStatus error = new RequestStatus(HttpStatus.BAD_REQUEST.toString(),"Missing Request Parameters");
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    
    }
	
	@ExceptionHandler(DBException.class)
    public ResponseEntity<RequestStatus> exceptionHandler(DBException e){
    	logger.error(e);
    	return new ResponseEntity<>(new RequestStatus(e.getErrCode(),e.getErrDesc()),HttpStatus.BAD_REQUEST);
    
    }
	
	@ExceptionHandler(MailerException.class)
    public ResponseEntity<RequestStatus> exceptionHandler(MailerException e){
    	logger.error(e);
    	return new ResponseEntity<>(new RequestStatus(e.getErrCode(),e.getErrDesc()),HttpStatus.BAD_REQUEST);
    
    }
	
	@ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<RequestStatus> exceptionHandler(NumberFormatException e){
    	logger.error(e);
    	RequestStatus error = new RequestStatus(HttpStatus.BAD_REQUEST.toString(),"Validation Exception: Non Numerical Field");
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    
    }
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<RequestStatus> exceptionHandler(HttpMessageNotReadableException e){
    	logger.error(e);
    	RequestStatus error = new RequestStatus(HttpStatus.BAD_REQUEST.toString(),"Validation Exception: Invalid Field");
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    
    }
	
	
	@ExceptionHandler(ValidationException.class)
    public ResponseEntity<RequestStatus> exceptionHandler(ValidationException e){
    	logger.error(e);
    	RequestStatus error = new RequestStatus(HttpStatus.UNPROCESSABLE_ENTITY.toString(),e.getMessage());
		return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    
    }
	
	@ExceptionHandler({MethodArgumentNotValidException.class,MethodArgumentTypeMismatchException.class})
	public ResponseEntity<RequestStatus> exceptionHandler(MethodArgumentNotValidException ex) {
		logger.error(ex);
		RequestStatus error = new RequestStatus(HttpStatus.BAD_REQUEST.toString(),"Validation Exception");
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<RequestStatus> exceptionHandler(HttpMediaTypeNotSupportedException ex) {
		logger.error(ex);
		RequestStatus error = new RequestStatus(HttpStatus.METHOD_NOT_ALLOWED.toString(),"Unsupported Media Type");
		return new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<RequestStatus> exceptionHandler(Exception ex) {

		logger.error(ex);
		RequestStatus error = new RequestStatus("500","Please contact your administrator");
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
