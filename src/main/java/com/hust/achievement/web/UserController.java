package com.hust.achievement.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hust.achievement.domain.Achievement;
import com.hust.achievement.domain.Student;
import com.hust.achievement.domain.User;
import com.hust.achievement.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController
{
	@Autowired
	private UserService userService;

	/**
	 * this method is the interface of login. Front end send student number and
	 * password to login.
	 * 
	 * @param httpSession
	 *            session
	 * @param student
	 *            object of student
	 * @return object of user
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/login")
	public User login(HttpSession httpSession, @RequestBody Student student) throws Exception
	{
		User user = null;

		user = userService.checkStudent(student);

		if (userService.getUser(user.getStudentNumber()) == null)
		{
			userService.createUser(user);
		}
		httpSession.setAttribute("user", user);
		return user;
	}

	/**
	 * this method is used for achieve given achievement.
	 * 
	 * @param httpSession
	 *            session
	 * @param parameters
	 *            map of parameters
	 * @return map of properties
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/achieve")
	public Map<String, String> achieve(HttpSession httpSession, @RequestBody Map<String, String> parameters)
			throws Exception
	{

		User user = checkLogin(httpSession);
		userService.achieve(parameters.get("achievementName"), user);

		Map<String, String> map = new HashMap<>();
		map.put("finish", "finish");
		return map;
	}

	/**
	 * this method is used for get a list of achievement done by given user.
	 * 
	 * @param httpSession
	 *            session
	 * @return chart
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/schedule")
	public List<Achievement> schedule(HttpSession httpSession) throws Exception
	{
		List<Achievement> schedule = null;
		User user = checkLogin(httpSession);
		schedule = userService.getDoneAchievements(user);
		return schedule;
	}

	/**
	 * this method is used for user to sign in.
	 * 
	 * @param httpSession
	 *            session
	 * @return a map of properties
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/signin")
	public Map<String, String> signIn(HttpSession httpSession) throws Exception
	{
		Integer days = null;
		User user = checkLogin(httpSession);
		days = userService.signIn(user);
		Map<String, String> map = new HashMap<>();
		map.put("days", days.toString());
		return map;

	}

	/**
	 * this method is used for judge whether user has login.
	 * 
	 * @param httpSession
	 *            session
	 * @return the object of user
	 * @throws Exception
	 */
	private User checkLogin(HttpSession httpSession) throws Exception
	{
		User user = (User) httpSession.getAttribute("user");
		if (user == null)
		{
			throw new Exception("Not Login!");
		}
		return user;
	}

}
