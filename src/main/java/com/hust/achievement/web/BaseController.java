package com.hust.achievement.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Controller
public class BaseController
{
	/**
	 * this method returns all the exceptions to the front end.
	 * 
	 * @param exception
	 *            exception sent to front end
	 * @return the given exception
	 */
	
	@ExceptionHandler
	public ResponseEntity<Object> exp(Exception exception)
	{
		Map<String, String> map = new HashMap<>();
		map.put("message", exception.getMessage());
		return new ResponseEntity<Object>(map, HttpStatus.FORBIDDEN);
	}
}
