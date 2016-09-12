package com.hust.achievement.service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.hust.achievement.domain.User;
import com.hust.achievement.mapper.CategoryMapper;
import com.hust.achievement.mapper.UserMapper;

@Transactional
@Service
public class AchievementService
{
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private CategoryMapper categoryMapper;

	/**
	 * this method is used for getting the chart from MySQL database and set it
	 * in the application.
	 */
	public void setTopNames()
	{
		// get the application context
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();

		// construct the chart
		ConcurrentHashMap<String, List<User>> concurrentHashMap = new ConcurrentHashMap<String, List<User>>();
		List<String> names = categoryMapper.selectAllCategoryNames();
		for (String category : names)
		{
			concurrentHashMap.put(category, userMapper.selectTopUsers(category));
		}
		concurrentHashMap.put("Top5", userMapper.selectTopUsers(null));

		// set the attribute
		webApplicationContext.getServletContext().setAttribute("chart", concurrentHashMap);
	}

	/**
	 * this method is used for getting the chart from the application.
	 * 
	 * @return a map represent the chart.
	 * @throws Exception
	 */
	public ConcurrentHashMap<String, List<User>> getTopNames() throws Exception
	{
		// get the application context
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();

		// get the chart
		Object chart = webApplicationContext.getServletContext().getAttribute("chart");
		ConcurrentHashMap<String, List<User>> concurrentHashMap;
		if (chart instanceof ConcurrentHashMap<?, ?>)
		{
			concurrentHashMap = (ConcurrentHashMap<String, List<User>>) chart;
		}
		else
		{
			throw new Exception("fail to get chart!");
		}
		return concurrentHashMap;
	}
}
