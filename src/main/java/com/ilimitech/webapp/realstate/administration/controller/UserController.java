package com.ilimitech.webapp.realstate.administration.controller;

import com.ilimitech.webapp.realstate.administration.dto.UserDto;
import com.ilimitech.webapp.realstate.administration.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public String listRegisteredUsers(Model model) {
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        UserDto user = userService.findById(id);
        model.addAttribute("user", user);
        return "edit";
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute UserDto user) {
        userService.updateUser(user);
        return "redirect:/users"; // Redirige a la página de usuarios registrados
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        UserDetails currentUserDetails = (UserDetails) authentication.getPrincipal();
        boolean isAdmin = currentUserDetails.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));

        UserDto userToDelete = userService.findById(id);

        if (!isAdmin) {
            throw new AccessDeniedException("You do not have permission to delete users.");
        }

        if (userToDelete.getUserName().equals(currentUsername)) {
            throw new IllegalArgumentException("You cannot delete yourself.");
        }

        userService.delete(id);
        return "redirect:/users/list";
    }

}
