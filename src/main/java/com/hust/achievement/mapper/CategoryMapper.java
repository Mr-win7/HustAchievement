package com.hust.achievement.mapper;

import java.util.List;

import com.hust.achievement.domain.Category;
import com.hust.achievement.domain.CategoryExample;

public interface CategoryMapper
{
	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table category
	 *
	 * @mbggenerated Thu Jul 21 15:27:01 CST 2016
	 */
	int countByExample(CategoryExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table category
	 *
	 * @mbggenerated Thu Jul 21 15:27:01 CST 2016
	 */
	List<Category> selectByExampleWithBLOBs(CategoryExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table category
	 *
	 * @mbggenerated Thu Jul 21 15:27:01 CST 2016
	 */
	List<Category> selectByExample(CategoryExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table category
	 *
	 * @mbggenerated Thu Jul 21 15:27:01 CST 2016
	 */
	Category selectByPrimaryKey(String name);

	/**
	 * This method is used for getting all the names of categories.
	 * 
	 * @return name of categoriesĄŁ
	 */
	List<String> selectAllCategoryNames();
}