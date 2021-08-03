package ru.aseev.jm231.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aseev.jm231.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    User findByName(String name);
}
