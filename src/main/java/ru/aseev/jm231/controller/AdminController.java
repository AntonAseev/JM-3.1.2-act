package ru.aseev.jm231.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.aseev.jm231.model.Role;
import ru.aseev.jm231.model.User;
import ru.aseev.jm231.service.UserService;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String allUsers(ModelMap model) {
        model.addAttribute("user", userService.allMyUsers());
        return "allUsers";
    }

    @PostMapping
    public String createUser(@ModelAttribute("user") User user,
                             @ModelAttribute("role") String role) {
            Role newRole = new Role(role.equals("ADMIN") ? 1L : 2L, "ROLE" + role);
            Set<Role> roles = new HashSet<>();
            roles.add(newRole);
            user.setRoles(roles);
            userService.saveNewUser(user);
            return "redirect:/admin";
    }

    @PatchMapping
    public String updateUser(@ModelAttribute("user") User user,
                             @ModelAttribute("role") String role) {
        Role newRole = new Role(role.equals("ADMIN") ? 1L : 2L, "ROLE" + role);
        Set<Role> roles = new HashSet<>();
        roles.add(newRole);
        user.setRoles(roles);
        userService.saveNewUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.dropUser(id);
        return "redirect:/admin";
    }
}
