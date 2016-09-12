package com.hust.achievement.web;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hust.achievement.domain.User;
import com.hust.achievement.service.AchievementService;

@Controller
@RequestMapping(value = "/achievement")
public class AchievementController extends BaseController
{
	@Autowired
	private AchievementService achievementService;

	@ResponseBody
	@RequestMapping(value = "/top")
	public ConcurrentHashMap<String, List<User>> top() throws Exception
	{
		return achievementService.getTopNames();
	}
}
