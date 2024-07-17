package com.ilimitech.webapp.realstate.administration.controller;


import com.ilimitech.webapp.realstate.administration.dto.UserDto;
import com.ilimitech.webapp.realstate.administration.service.UserService;
import com.ilimitech.webapp.realstate.administration.util.CaptchaUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@AllArgsConstructor
@Controller
public class LoginController {

    private final UserService userService;

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("register")
    public String showRegistrationForm(Model model) {
        UserDto user = new UserDto();
        CaptchaUtil.getCaptcha(user);
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public ModelAndView registration(@Valid @ModelAttribute("user") UserDto user,
                                     BindingResult result,
                                     RedirectAttributes redirectAttributes) {
//        User existing = userService.findByUserNameOrEmail(user.getUserName(),user.getEmail());
        if (user.getCaptcha().equals(user.getHiddenCaptcha())) {

            ModelAndView mavError = validateInput(user, result, redirectAttributes);
            if (mavError != null) return mavError;

            user.setActive(true);
            userService.saveUser(user);
            redirectAttributes.addFlashAttribute("successMessage", "Usuario registrado con éxito");
            return new ModelAndView("redirect:/login?success");
        } else {
//            ModelAndView mav = new ModelAndView("redirect:/register");
            ModelAndView mav = new ModelAndView("/register");
            CaptchaUtil.getCaptcha(user);
            mav.addObject("user", user);
            mav.addObject("captchaErrorMessage", "Invalid Captcha");
//            redirectAttributes.addFlashAttribute("captchaErrorMessage", "Invalid Captcha");
            return mav;
//            return errorInRegistration("captchaErrorMessage",user, redirectAttributes, "There is already an account registered with that userName");
        }
    }

    private ModelAndView validateInput(UserDto user, BindingResult result, RedirectAttributes redirectAttributes) {
        if (userService.existsByUsername(user.getUserName())) {
            return errorInRegistration("fieldUserNameError",user, redirectAttributes, "There is already an account registered with that userName");
        }
        if (userService.existsByEmail(user.getEmail())) {
            return errorInRegistration("fieldEmailError",user, redirectAttributes, "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            return errorInRegistration("someError",user, redirectAttributes, "Some Error In registration");
        }
        return null;
    }

    private static ModelAndView errorInRegistration(String fieldName, UserDto user, RedirectAttributes redirectAttributes, String errorCause) {
        ModelAndView mav;
        mav = new ModelAndView("redirect:/register");
        CaptchaUtil.getCaptcha(user);
        mav.addObject("user", user);
        redirectAttributes.addFlashAttribute(fieldName, errorCause);
        return mav;
    }

    @GetMapping("/check-username")
    public ResponseEntity<Boolean> checkUsername(@RequestParam("value") String username) {
        boolean exists = userService.existsByUsername(username);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/check-email")
    public ResponseEntity<Boolean> checkEmail(@RequestParam("value") String email) {
        boolean exists = userService.existsByEmail(email);
        return ResponseEntity.ok(exists);
    }
}
