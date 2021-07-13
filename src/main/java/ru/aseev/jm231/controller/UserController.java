package ru.aseev.jm231.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.aseev.jm231.dao.UserDao;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    final UserDao userDao;

    @Autowired
    UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping()
    public String getUserPage(Principal principal, Model model) {
        model.addAttribute("user", userDao.getUserByName(principal.getName()));
        return "user";
    }
}
