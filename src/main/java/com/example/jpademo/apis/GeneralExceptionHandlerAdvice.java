package com.example.jpademo.apis;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.jpademo.exceptions.DomainValidationException;
import com.example.jpademo.exceptions.RecordNotFoundException;
import com.example.jpademo.utils.AppUtils;
import com.example.jpademo.viewmodels.ValidationErrorPageViewModel;

@ControllerAdvice
public class GeneralExceptionHandlerAdvice {

	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<?> handleRecordNotFoundException(RecordNotFoundException rnfe) {
		Map<String, String> map = new HashMap<>();
		map.put("message", rnfe.getMessage());
		return ResponseEntity.status(400).body(map);
	}
	
	@ExceptionHandler(DomainValidationException.class)
	public ResponseEntity<?> handleDomainValidationException(DomainValidationException dve) {
		Map<String, String> map = new HashMap<>();
		map.put("message", dve.getMessage());
		return ResponseEntity.status(400).body(map);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorPageViewModel> handleValidationException(MethodArgumentNotValidException exception) {
        return ResponseEntity.badRequest().body(AppUtils.getValidationErrorPage(exception));
    }
}
