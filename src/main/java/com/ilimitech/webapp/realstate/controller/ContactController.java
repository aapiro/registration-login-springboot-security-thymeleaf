package com.ilimitech.webapp.realstate.controller;

import com.ilimitech.webapp.realstate.mapper.ContactFormMapper;
import com.ilimitech.webapp.realstate.repository.ContactFormEntityRepository;
import com.ilimitech.webapp.realstate.dto.ContactFormDto;
import com.ilimitech.webapp.realstate.service.PropertyService;
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
    private final ContactFormMapper contactFormMapper;
    private PropertyService propertyService;

    @PostMapping(value = "/property/contact")
    public ModelAndView handleContactForm(@Valid @ModelAttribute("contactForm") ContactFormDto contactForm,
                                          RedirectAttributes redirectAttributes) {

        ModelAndView mav = new ModelAndView("redirect:/property/" + contactForm.getPropertyId());
        if (contactForm.getCaptcha().equals(contactForm.getHiddenCaptcha())) {

            contactFormEntityRepository.save(contactFormMapper.toEntity(contactForm,propertyService));
            redirectAttributes.addFlashAttribute("successMessage", "Formulario enviado con éxito");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Invalid Captcha");
        }
        return mav;
    }
}
