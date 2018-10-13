package ru.stacy.dao;

import ru.stacy.model.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);
    void updateUser(User user);
    void removeUser(int id);
    void removeAllUser();
    User getUserById(int id);
    List<User> listUsers();
}
