package com.hust.achievement.domain;

public class Days {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column days.student_number
     *
     * @mbggenerated Thu Jul 21 15:27:01 CST 2016
     */
    private String studentNumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column days.days
     *
     * @mbggenerated Thu Jul 21 15:27:01 CST 2016
     */
    private Integer days;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column days.student_number
     *
     * @return the value of days.student_number
     *
     * @mbggenerated Thu Jul 21 15:27:01 CST 2016
     */
    public String getStudentNumber() {
        return studentNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column days.student_number
     *
     * @param studentNumber the value for days.student_number
     *
     * @mbggenerated Thu Jul 21 15:27:01 CST 2016
     */
    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber == null ? null : studentNumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column days.days
     *
     * @return the value of days.days
     *
     * @mbggenerated Thu Jul 21 15:27:01 CST 2016
     */
    public Integer getDays() {
        return days;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column days.days
     *
     * @param days the value for days.days
     *
     * @mbggenerated Thu Jul 21 15:27:01 CST 2016
     */
    public void setDays(Integer days) {
        this.days = days;
    }
}