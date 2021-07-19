package ru.aseev.jm231.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.aseev.jm231.dao.UserDaoImpl;
import ru.aseev.jm231.model.Role;
import ru.aseev.jm231.model.User;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserDaoImpl userDaoImpl;

    @Autowired
    public AdminController(UserDaoImpl userDaoIml) {
        this.userDaoImpl = userDaoIml;
    }

    @GetMapping()
    public String allUsers(Model model) {
        model.addAttribute("users", userDaoImpl.allMyUsers());
        return "allUsers";
    }

    @GetMapping("/{id}")
    public String getUniqueUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userDaoImpl.uniqueMyUser(id));
        return "userForAdmin";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping
    public String createUser(@ModelAttribute("user") User user,
                             @ModelAttribute("role") String role) {
            Role newRole = new Role(role.equals("ADMIN") ? 1L : 2L, "ROLE" + role);
            Set<Role> roles = new HashSet<>();
            roles.add(newRole);
            user.setRoles(roles);
            userDaoImpl.saveNewUser(user);
            return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String editUser(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userDaoImpl.uniqueMyUser(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user,
                             @PathVariable("id") long id) {
            userDaoImpl.changeUser(id, user);
            return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userDaoImpl.dropUser(id);
        return "redirect:/admin";
    }
}
