package com.ilimitech.webapp.realstate.controller;

import com.ilimitech.webapp.realstate.dto.PropertyDto;
import com.ilimitech.webapp.realstate.entity.PropertyEntity;
import com.ilimitech.webapp.realstate.service.PropertyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/dashboard")
public class DashboardController {

    private final PropertyService propertyDashboardService;

    @GetMapping("")
    public ModelAndView getDashboard() {
        return new ModelAndView("realstate/dashboard/index");
    }
    @GetMapping("/property/list")
    public ModelAndView getAllProperties() {
        return new ModelAndView("realstate/dashboard/property-list");
    }

    @GetMapping("/property/new")
    public ModelAndView addProperty2() {
        ModelAndView mav = new ModelAndView("realstate/dashboard/property-form");
        List<String> propertyTypeOptions = Arrays.asList("Option 1", "Option 2", "Option 3");
        PropertyDto propertyDto = new PropertyDto();
        mav.addObject("propertyDto", propertyDto);
        mav.addObject("pageTitle", "Create new PropertyFrontendEntity");
        mav.addObject("propertyTypeOptions", propertyTypeOptions);
        mav.addObject("propertyRadioOptions", propertyTypeOptions);
        List<String> energyQualificationOptions = Arrays.asList("A", "B", "No tiene");
        List<String> orientationOptions = Arrays.asList("Norte", "Sur", "Este", "Oeste");
        mav.addObject("energyQualification", energyQualificationOptions);
        mav.addObject("orientationOptions", orientationOptions);
        mav.addObject("propertyTypes", List.of("Casa", "Casa de campo", "Apartamento", "Terreno"));
        mav.addObject("operationTypes", List.of("Venta", "Alquiler"));
        return mav;
    }
    @PostMapping("/property/save")
    public ModelAndView saveProperty(PropertyDto propertyDto) {
//    public String saveProperty(PropertyDto propertyDto, RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView("realstate/dashboard/images");
        try {
            System.out.printf("PROPIEDAD A GUARDAR: "+ propertyDto.toString());

            PropertyEntity propertyEntity = propertyDashboardService.saveProperty(propertyDto);
            long userId = 1;
            long itemId = propertyEntity.getId();
            String imageServerUrl = "http://localhost:8081";
            mav.addObject("userId",String.valueOf(userId));
            mav.addObject("itemId",String.valueOf(itemId));
            mav.addObject("imageServerUrl",imageServerUrl);
//            redirectAttributes.addFlashAttribute("message", "The PropertyFrontendEntity has been saved successfully!");
        } catch (Exception e) {
            log.error(e.toString());

//            redirectAttributes.addAttribute("message", e.getMessage());
        }
//        return "redirect:/property/new";
        return mav;
    }
}
