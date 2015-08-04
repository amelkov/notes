package by.amelkov.dao;

import by.amelkov.model.User;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserImplement implements UserDAO {

    public User getUser(String login, String password) {
        Statement statement = null;
        DBConnection connection = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = new DBConnection();
            connection.createConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM users WHERE login = '"+login+"' AND password = '"+password+"';");
            while (resultSet.next()) {
                user = new User(resultSet.getString("login"),resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(resultSet != null)resultSet.close();
                if(statement != null)statement.close();
                if(connection != null)connection.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    public User checkUser(String login) {
        Statement statement = null;
        DBConnection connection = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = new DBConnection();
            connection.createConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT login FROM users WHERE login = '"+login+"';");
            while (resultSet.next()) {
                user = new User(resultSet.getString("login"), null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(resultSet != null)resultSet.close();
                if(statement != null)statement.close();
                if(connection != null)connection.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }
}
