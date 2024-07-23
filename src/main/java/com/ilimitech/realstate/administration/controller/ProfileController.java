package com.ilimitech.realstate.administration.controller;

import com.ilimitech.realstate.administration.dto.UserDto;
import com.ilimitech.realstate.administration.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/administrator")
public class ProfileController {

    private UserService userService;

    @GetMapping("/profile")
    public String showProfile(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        UserDto userDto = userService.findByUsernameProfile(userDetails.getUsername());
        model.addAttribute("user", userDto);
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(UserDto userDto) {
        userService.updateUser(userDto);
        return "redirect:/administrator/profile?success";
    }
}