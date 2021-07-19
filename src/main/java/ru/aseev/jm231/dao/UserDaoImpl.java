package ru.aseev.jm231.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aseev.jm231.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class UserDaoImpl {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private UserDao userDao;

    @Transactional
    public User getUserByName(String name) {
            return em.createQuery("SELECT u FROM User u WHERE u.name = :name", User.class)
                    .setParameter("name", name).getSingleResult();
    }

    @Transactional
    public User uniqueMyUser(long id) {
        return userDao.getById(id);
    }

    @Transactional
    public void saveNewUser(User user){
        userDao.save(user);
    }

    @Transactional
    public List<User> allMyUsers() {
        return userDao.findAll();
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
        userDao.deleteById(id);
    }
}

