package edu.cms.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalException {
@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?>resourcenotFoundHandling(ResourceNotFoundException
			exception ,WebRequest request)
	{
		ErrorDetails errordetails=new ErrorDetails(new Date(),exception.getMessage(),
				request.getDescription(false));
	
		return new ResponseEntity<>(errordetails,HttpStatus.NOT_FOUND);
	}
}
