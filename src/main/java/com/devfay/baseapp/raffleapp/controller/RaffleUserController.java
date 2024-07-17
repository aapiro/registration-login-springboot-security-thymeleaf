package com.devfay.baseapp.raffleapp.controller;

import com.devfay.baseapp.raffleapp.model.User;
import com.devfay.baseapp.raffleapp.service.RaffleUserService;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/raffle/users")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class RaffleUserController {

    private RaffleUserService raffleUserService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        return "raffle/register";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        return "raffle/login";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, Model model) {
        raffleUserService.register(user);
        model.addAttribute("message", "UserEntity registered successfully!");
        return "redirect:/raffle/users/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model) {
        // Authentication logic
        return "redirect:/raffle/raffles";
    }
}
