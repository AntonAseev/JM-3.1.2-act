package ru.aseev.jm231.dao;

import ru.aseev.jm231.model.User;

import java.util.List;

public interface UserDao {
    public User uniqueMyUser(long id);
    public List<User> allMyUsers();
    public void saveNewUser(User user);
    public void changeUser(long id, User updateUser);
    public void dropUser(long id);
    public User getUserByName(String name);
}
