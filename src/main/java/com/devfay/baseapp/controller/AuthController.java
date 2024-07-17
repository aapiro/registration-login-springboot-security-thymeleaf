package com.devfay.baseapp.controller;

import com.devfay.baseapp.dto.UserDto;
import com.devfay.baseapp.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@AllArgsConstructor
@Controller
public class AuthController {

    private final UserService userService;

    @GetMapping("index")
    public String home(){
        return "index";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    // handler method to handle user registration request
    @GetMapping("register")
    public String showRegistrationForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    // handler method to handle register user form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto user,
                               BindingResult result,
                               Model model){
//        UserEntity existing = userService.findByUserNameOrEmail(user.getUserName(),user.getEmail());
        if (userService.existsByUsername(user.getUserName())) {
            result.rejectValue("userName", null, "There is already an account registered with that userName");
        }
        if (userService.existsByEmail(user.getEmail())) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        user.setActive(true);
        userService.saveUser(user);
        return "redirect:/register?success";
    }
}
