package ru.aseev.jm231.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.aseev.jm231.dao.UserDao;
import ru.aseev.jm231.model.Role;
import ru.aseev.jm231.model.User;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    final UserDao userDao;

    @Autowired
    public AdminController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping()
    public String allUsers(Model model) {
        model.addAttribute("users", userDao.allMyUsers());
        return "allUsers";
    }

    @GetMapping("/{id}")
    public String getUniqueUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userDao.uniqueMyUser(id));
        return "userForAdmin";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping
    public String createUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult
                           , @ModelAttribute("role") String role) {
        if (bindingResult.hasErrors()) {
            return "new";
        } else {
            Role newRole = new Role(role.equals("ADMIN") ? 1L : 2L, "ROLE" + role);
            Set<Role> roles = new HashSet<>();
            roles.add(newRole);
            user.setRoles(roles);
            userDao.saveNewUser(user);
            return "redirect:/admin";
        }
    }

    @GetMapping("/{id}/edit")
    public String editUser(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userDao.uniqueMyUser(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                             @PathVariable("id") long id) {
        if (bindingResult.hasErrors()) {
            return "edit";
        } else {
            userDao.changeUser(id, user);
            return "redirect:/admin";
        }
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userDao.dropUser(id);
        return "redirect:/admin";
    }

}
