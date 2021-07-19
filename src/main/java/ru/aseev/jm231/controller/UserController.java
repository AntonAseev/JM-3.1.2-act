package ru.aseev.jm231.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.aseev.jm231.dao.UserDaoImpl;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserDaoImpl userDaoImpl;

    @Autowired
    public UserController(UserDaoImpl userDaoImpl) {
        this.userDaoImpl = userDaoImpl;
    }

    @GetMapping()
    public String getUserPage(Principal principal, Model model) {
        model.addAttribute("user", userDaoImpl.getUserByName(principal.getName()));
        return "user";
    }
}
