package by.amelkov.dao;

import by.amelkov.model.User;

import java.util.List;

public interface UserDAO {
    User checkUser(String login);
    User getUser(String login, String password);
}
