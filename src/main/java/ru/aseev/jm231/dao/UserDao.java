package ru.aseev.jm231.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aseev.jm231.model.User;


public interface UserDao extends JpaRepository<User, Long> {
    User findByName(String name);
}
