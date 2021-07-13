package ru.aseev.jm231.dao;

import org.springframework.transaction.annotation.Transactional;
import ru.aseev.jm231.model.Role;
import ru.aseev.jm231.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public User getUserByName(String name) {
            User user = em.createQuery("SELECT u FROM User u WHERE u.name = :name", User.class)
                    .setParameter("name", name).getSingleResult();
            return user;
    }

    @Transactional
    public User uniqueMyUser(long id) {
        return em.find(User.class, id);
    }

    @Transactional
    public void saveNewUser(User user){
        em.persist(em.merge(user));
    }

    @Transactional
    public List<User> allMyUsers() {
        return em.createQuery("SELECT e FROM User e", User.class).getResultList();
    }

    @Transactional
    public void changeUser (long id, User updatedUser) {
        User changedUser = em.find(User.class, id);
        changedUser.setName(updatedUser.getName());
        changedUser.setAge(updatedUser.getAge());
        changedUser.setEmail(updatedUser.getEmail());
        changedUser.setPassword(updatedUser.getPassword());
        em.merge(changedUser);
    }

    @Transactional
    public void dropUser(long id){
        em.remove(uniqueMyUser(id));
    }
}

