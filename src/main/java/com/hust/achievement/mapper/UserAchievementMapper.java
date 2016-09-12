package com.hust.achievement.mapper;

import com.hust.achievement.domain.UserAchievement;
import com.hust.achievement.domain.UserAchievementExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserAchievementMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users_achievement
     *
     * @mbggenerated Thu Jul 21 15:27:01 CST 2016
     */
    int countByExample(UserAchievementExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users_achievement
     *
     * @mbggenerated Thu Jul 21 15:27:01 CST 2016
     */
    int insert(UserAchievement record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users_achievement
     *
     * @mbggenerated Thu Jul 21 15:27:01 CST 2016
     */
    int insertSelective(UserAchievement record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users_achievement
     *
     * @mbggenerated Thu Jul 21 15:27:01 CST 2016
     */
    List<UserAchievement> selectByExample(UserAchievementExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users_achievement
     *
     * @mbggenerated Thu Jul 21 15:27:01 CST 2016
     */
    UserAchievement selectByPrimaryKey(@Param("studentNumber") String studentNumber, @Param("achievementName") String achievementName);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users_achievement
     *
     * @mbggenerated Thu Jul 21 15:27:01 CST 2016
     */
    int updateByExampleSelective(@Param("record") UserAchievement record, @Param("example") UserAchievementExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users_achievement
     *
     * @mbggenerated Thu Jul 21 15:27:01 CST 2016
     */
    int updateByExample(@Param("record") UserAchievement record, @Param("example") UserAchievementExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users_achievement
     *
     * @mbggenerated Thu Jul 21 15:27:01 CST 2016
     */
    int updateByPrimaryKeySelective(UserAchievement record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users_achievement
     *
     * @mbggenerated Thu Jul 21 15:27:01 CST 2016
     */
    int updateByPrimaryKey(UserAchievement record);
}