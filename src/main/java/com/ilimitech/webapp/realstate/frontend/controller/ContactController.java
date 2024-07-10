package com.ilimitech.webapp.realstate.frontend.controller;

import com.ilimitech.webapp.realstate.entity.ContactFormEntityMapper;
import com.ilimitech.webapp.realstate.entity.ContactFormEntityRepository;
import com.ilimitech.webapp.realstate.frontend.dto.ContactFormDto;
import com.ilimitech.webapp.realstate.frontend.service.PropertyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
public class ContactController {

    private ContactFormEntityRepository contactFormEntityRepository;
    private final ContactFormEntityMapper contactFormEntityMapper;
    private PropertyService propertyService;

    @PostMapping(value = "/property/contact")
    public ModelAndView handleContactForm(@Valid @ModelAttribute("contactForm") ContactFormDto contactForm,
                                          RedirectAttributes redirectAttributes) {

        ModelAndView mav = new ModelAndView("redirect:/property/" + contactForm.getPropertyId());
        if (contactForm.getCaptcha().equals(contactForm.getHiddenCaptcha())) {

            contactFormEntityRepository.save(contactFormEntityMapper.toEntity(contactForm,propertyService));
            redirectAttributes.addFlashAttribute("successMessage", "Formulario enviado con éxito");
            return mav;
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Invalid Captcha");
            return mav;
        }
    }
}
