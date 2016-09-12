package com.hust.achievement.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hust.achievement.service.AchievementService;

public class initListener implements ServletContextListener
{
	volatile private static ApplicationContext applicationContext = null;

	@Override
	public void contextInitialized(ServletContextEvent sce)
	{

		applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext());
		AchievementService achievementService = (AchievementService) applicationContext.getBean("AchievementService");
		achievementService.setTopNames();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce)
	{
		sce.getServletContext().removeAttribute("chart");
	}

	public static ApplicationContext getApplicationContext()
	{
		return applicationContext;
	}

}
