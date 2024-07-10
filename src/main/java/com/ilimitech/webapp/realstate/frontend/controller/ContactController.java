package com.ilimitech.webapp.realstate.frontend.controller;

import com.ilimitech.webapp.realstate.frontend.dto.ContactFormDto;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

    @PostMapping(value = "/property/contact")
    public String handleContactForm(ContactFormDto contactForm, Model model) {
        // Aquí puedes procesar los datos del formulario, por ejemplo, guardarlos en la base de datos, enviarlos por email, etc.

        // Ejemplo simple: imprimir los datos del formulario en la consola
        System.out.println("Nombre: " + contactForm.getName());
        System.out.println("Email: " + contactForm.getEmail());
        System.out.println("Asunto: " + contactForm.getSubject());
        System.out.println("Mensaje: " + contactForm.getMessage());
//        redirectAttributes.addFlashAttribute("message", "Formulario enviado con éxito");
        model.addAttribute("message","Formulation");
        // Responder con un mensaje de éxito
        return "forward:/property/"+ contactForm.getPropertyId();
//        return "redirect:/property/"+contactForm.getPropertyId();
    }
}
