package com.ilimitech.test;

import com.ilimitech.webapp.realstate.dto.FormDataDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class FormController {

    @GetMapping("/form")
    public String showForm(Model model) {
        FormDataDto formDataDto = new FormDataDto();
        model.addAttribute("formData", formDataDto);

        // Sample options
        List<String> selectOptions = Arrays.asList("Option 1", "Option 2", "Option 3");
        List<String> radioOptions = Arrays.asList("Radio 1", "Radio 2", "Radio 3");
        List<String> checkboxOptions = Arrays.asList("Checkbox 1", "Checkbox 2", "Checkbox 3");

        model.addAttribute("selectOptions", selectOptions);
        model.addAttribute("radioOptions", radioOptions);
        model.addAttribute("checkboxOptions", checkboxOptions);

        return "dashboard/form-template-example";
    }

    @PostMapping("/submit-form")
    public String submitForm(@ModelAttribute FormDataDto formDataDto, Model model) {
        model.addAttribute("formData", formDataDto);
        return "dashboard/form-template-example";
    }
}
