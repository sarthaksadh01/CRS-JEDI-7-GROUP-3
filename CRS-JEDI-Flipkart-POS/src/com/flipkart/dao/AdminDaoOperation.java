package com.flipkart.dao;

import com.flipkart.constant.SQLQueriesConstants;
import com.flipkart.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDaoOperation implements AdminDaoInterface {

    @Override
    public void addProfessor(String name, String gender, String password, String address, String designation, String department) {

        Connection connection = DBUtil.getConnection();
        try {
            int userId;

            String generatedColumns[] = { "userId" };

            PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.ADD_USER_QUERY, generatedColumns);
            statement.setString(1,password);
            statement.setString(2, "P");
            statement.setString(3, name);
            statement.setString(4, address);
            statement.setString(5, gender);
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next()){
                userId = rs.getInt(1);

                statement = connection.prepareStatement(SQLQueriesConstants.ADD_PROFESSOR);
                statement.setString(2, department);
                statement.setString(3, designation);
                statement.setInt(1, userId);
                statement.executeUpdate();
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void approveStudent(int studentId) {
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.APPROVE_STUDENT);
            statement.setInt(1, studentId);
            statement.executeUpdate();
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void addCourse(String courseName, int instructorID, int semester) {
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.ADD_COURSE);
            statement.setString(1, courseName);
            statement.setInt(2, instructorID);
            statement.setInt(3, semester);
            statement.executeUpdate();
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void deleteCourse(int courseID) {
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.DELETE_COURSE);
            statement.setInt(1, courseID);
            statement.executeUpdate();
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
}