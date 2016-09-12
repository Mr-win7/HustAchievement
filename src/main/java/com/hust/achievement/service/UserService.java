package com.hust.achievement.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hust.achievement.domain.Achievement;
import com.hust.achievement.domain.Days;
import com.hust.achievement.domain.Student;
import com.hust.achievement.domain.User;
import com.hust.achievement.domain.UserAchievement;
import com.hust.achievement.mapper.AchievementMapper;
import com.hust.achievement.mapper.DaysMapper;
import com.hust.achievement.mapper.UserAchievementMapper;
import com.hust.achievement.mapper.UserMapper;

@Transactional
@Service
public class UserService
{
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserAchievementMapper userAchievementMapper;

	@Autowired
	private DaysMapper daysMapper;

	@Autowired
	private AchievementMapper achievementMapper;

	/**
	 * this method is used for creating an account of user and setting the
	 * relationship of different columns in MySQL.
	 * 
	 * @param user
	 *            given user.
	 */
	public void createUser(User user)
	{
		// set the user column
		userMapper.insert(user);

		// set the days column
		Days days = new Days();
		days.setDays(0);
		days.setStudentNumber(user.getStudentNumber());
		daysMapper.insert(days);

		// set the users_achievement column
		List<String> names = achievementMapper.selectAllAchievementNames();
		UserAchievement userAchievement = null;
		for (String name : names)
		{
			userAchievement = new UserAchievement();
			userAchievement.setAchievementName(name);
			userAchievement.setStatus("undone");
			userAchievement.setStudentNumber(user.getStudentNumber());
			userAchievementMapper.insert(userAchievement);
		}

	}

	/**
	 * this method is used for get user with given student number.
	 * 
	 * @param studentNumber
	 *            the given student number.
	 * @return user with given student.
	 */
	public User getUser(String studentNumber)
	{
		return userMapper.selectByPrimaryKey(studentNumber);
	}

	/**
	 * this method is used for checking the given student number and password on
	 * HUB and turning the object of student to the object of user.
	 * 
	 * @param student
	 *            an object with student number and password on HUB.
	 * @return corresponding object of user.
	 * @throws Exception
	 */
	public User checkStudent(Student student) throws Exception
	{
		// construct the client of HttpClient
		RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).build();
		CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
		HttpGet httpGet1 = new HttpGet("http://s.hub.hust.edu.cn/index.jsp");
		HttpPost httpPost1 = new HttpPost("http://s.hub.hust.edu.cn/hublogin.action");
		HttpGet httpGet2 = new HttpGet("http://s.hub.hust.edu.cn/personal/search_1.action");
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

		// get the value of property named "ln"
		InputStream response = httpClient.execute(httpGet1).getEntity().getContent();
		String content = IOUtils.toString(response, "UTF-8");
		Pattern pattern = Pattern.compile("app[0-9]*\\.dc\\.hust\\.edu\\.cn");
		Matcher matcher = pattern.matcher(content);
		String match = null;
		while (matcher.find())
		{
			int start = matcher.start();
			int end = matcher.end();
			match = content.substring(start, end);
		}

		// construct the special request
		nameValuePairs.add(new BasicNameValuePair("username", student.getStudentNumber()));
		nameValuePairs.add(new BasicNameValuePair("password", student.getPassword()));
		nameValuePairs.add(new BasicNameValuePair("ln", match));
		httpPost1.setEntity(new UrlEncodedFormEntity(nameValuePairs));

		httpPost1.addHeader("Host", "s.hub.hust.edu.cn");
		httpPost1.addHeader("Connection", "keep-alive");
		httpPost1.addHeader("Cache-Control", "max-age=0");
		httpPost1.addHeader("Origin", "http://s.hub.hust.edu.cn");
		httpPost1.addHeader("Upgrade-Insecure-Requests", "1");
		httpPost1.addHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36");
		httpPost1.addHeader("Content-type", "application/x-www-form-urlencoded");
		httpPost1.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		httpPost1.addHeader("Referer", "http://s.hub.hust.edu.cn/index.jsp");
		httpPost1.addHeader("Accept-Encoding", "gzip, deflate");
		httpPost1.addHeader("Accept-Language", "zh-CN,zh;q=0.8");

		// login the HUB system
		httpClient.execute(httpPost1);

		// get the name of user
		response = httpClient.execute(httpGet2).getEntity().getContent();
		content = IOUtils.toString(response, "UTF-8");
		pattern = Pattern.compile("<div class=\"name3\">欢迎! (.*?)</div>");
		matcher = pattern.matcher(content);
		match = null;
		while (matcher.find())
		{
			int start = matcher.start(1);
			int end = matcher.end(1);
			match = content.substring(start, end);
		}
		if (match == null)
		{
			throw new Exception("Fail to login!");
		}

		// construct the object of user
		User user = new User();
		user.setName(match);
		user.setStudentNumber(student.getStudentNumber());

		return user;
	}

	/**
	 * this method is used for signing in
	 * 
	 * @param user
	 *            given user
	 * @return the sum of days that user has signed in
	 * @throws Exception
	 */
	public Integer signIn(User user) throws Exception
	{
		Days days = daysMapper.selectByPrimaryKey(user.getStudentNumber());
		if (days.getDays() < 30)
		{
			daysMapper.sign(user.getStudentNumber());
			return daysMapper.selectByPrimaryKey(user.getStudentNumber()).getDays();
		}
		else
		{
			throw new Exception("User has finished the task");
		}
	}

	/**
	 * this method is used for getting the achievement done by given user.
	 * 
	 * @param user
	 *            the given user
	 * @return list of achievement
	 */
	public List<Achievement> getDoneAchievements(User user)
	{
		return achievementMapper.selectAllDoneAchievements(user.getStudentNumber());
	}

	/**
	 * this method is used for given user achieving given achievement.
	 * 
	 * @param achievementName
	 *            name of achievement
	 * @param user
	 *            object of user
	 * @throws Exception
	 */
	public void achieve(String achievementName, User user) throws Exception
	{
		// judge the existence of the achievement
		if (achievementMapper.selectByPrimaryKey(achievementName) == null)
		{
			throw new Exception("No such achievement!");
		}
		else
		{
			UserAchievement userAchievement = new UserAchievement();
			userAchievement.setAchievementName(achievementName);
			userAchievement.setStatus("done");
			userAchievement.setStudentNumber(user.getStudentNumber());
			userAchievementMapper.updateByPrimaryKey(userAchievement);
		}

	}

}
