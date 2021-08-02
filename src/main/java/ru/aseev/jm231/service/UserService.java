package ru.aseev.jm231.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.aseev.jm231.model.User;

import java.util.List;

@Service
public interface UserService extends UserDetailsService {

    List<User> allMyUsers();
    User uniqueMyUser(long id);
    User getUserByName(String name);
    void saveNewUser(User user);
    void dropUser(long id);

}
