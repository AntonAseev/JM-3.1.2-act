package ru.aseev.jm231.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.aseev.jm231.dao.UserDaoImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDaoImpl userDaoImpl;

    @Autowired
    public UserDetailsServiceImpl(UserDaoImpl userDaoImpl) {
        this.userDaoImpl = userDaoImpl;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDaoImpl.getUserByName(username);
    }
}
