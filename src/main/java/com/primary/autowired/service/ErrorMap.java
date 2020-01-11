package com.primary.autowired.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class ErrorMap {

	static final private Logger logger = LoggerFactory.getLogger(ErrorMap.class);

	public ResponseEntity<?> errorMap(BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			Map<String, String> error = new LinkedHashMap<String, String>();
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				error.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return new ResponseEntity<Map<String, String>>(error, HttpStatus.OK);
		}
		return null;
	}
}
