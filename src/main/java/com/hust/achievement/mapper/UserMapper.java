package com.hust.achievement.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hust.achievement.domain.User;
import com.hust.achievement.domain.UserExample;

public interface UserMapper
{
	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table users
	 *
	 * @mbggenerated Thu Jul 21 15:27:01 CST 2016
	 */
	int countByExample(UserExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table users
	 *
	 * @mbggenerated Thu Jul 21 15:27:01 CST 2016
	 */
	int insert(User record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table users
	 *
	 * @mbggenerated Thu Jul 21 15:27:01 CST 2016
	 */
	int insertSelective(User record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table users
	 *
	 * @mbggenerated Thu Jul 21 15:27:01 CST 2016
	 */
	List<User> selectByExample(UserExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table users
	 *
	 * @mbggenerated Thu Jul 21 15:27:01 CST 2016
	 */
	User selectByPrimaryKey(String studentNumber);

	/**
	 * This method is used for getting members of the chart.
	 * 
	 * @param category
	 *            category of the chart.
	 * @return list of top users.
	 */
	List<User> selectTopUsers(@Param("category") String category);
}