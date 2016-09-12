package com.hust.achievement.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@ResponseBody
	@ExceptionHandler
	public Map<String,String> exp(Exception exception)
	{
		Map<String,String> map = new HashMap<>();
		map.put("Error", exception.getMessage());
		return map;
	}
}
