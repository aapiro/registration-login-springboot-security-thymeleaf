package com.ilimitech.webapp.realstate.controller;

import com.github.javafaker.Faker;
import com.ilimitech.webapp.realstate.bff.PropertyDetailResponse;
import com.ilimitech.webapp.realstate.dto.ContactFormDto;
import com.ilimitech.webapp.realstate.dto.LocationDto;
import com.ilimitech.webapp.realstate.dto.PropertyDto;
import com.ilimitech.webapp.realstate.dto.PropertyTypeDto;
import com.ilimitech.webapp.realstate.entity.PropertyEntity;
import com.ilimitech.webapp.realstate.mapper.PropertyMapper;
import com.ilimitech.webapp.realstate.mapper.PropertyTypeMapper;
import com.ilimitech.webapp.realstate.service.PortalService;
import com.ilimitech.webapp.realstate.service.PropertyService;
import com.ilimitech.webapp.realstate.util.Pair;
import com.ilimitech.webapp.realstate.util.SearchCriteria;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.ilimitech.webapp.realstate.administration.util.CaptchaUtil.getCaptcha;

@Slf4j
@Controller
@AllArgsConstructor
public class PropertyController {

    private PropertyService propertyService;
    private PortalService portalService;
    private final PropertyMapper propertyMapper;
    private final PropertyTypeMapper propertyTypeMapper;

    @GetMapping("/")
    public ModelAndView getFrontPortal() {
        ModelAndView mav = new ModelAndView("realstate/frontend/index");

//        IReactiveDataDriverContextVariable reactiveDataDrivenMode =
//                new ReactiveDataDriverContextVariable(propertyService.getAllProperties(), 1);
//        mav.addObject("properties", reactiveDataDrivenMode);

        List<PropertyDto> propertyEntityList = propertyService.getAllProperties();
        List<Pair<String,String>> carouselImages = portalService.getPortalCarouselImages();
        List<PropertyTypeDto> categoriesTypes = propertyService.getAllPropertyTypes();
        List<LocationDto> locationDtos = getLocations();

        mav.addObject("description", "Hola Mundo con ModelAndView");
        mav.addObject("aboutTitle", "#1 Para encontrar tu lugar especial");
        mav.addObject("aboutDescription", "#1 Para encontrar tu lugar especial");
        mav.addObject("properties", propertyEntityList);
        mav.addObject("carouselImages", carouselImages);
        mav.addObject("categories", categoriesTypes);
        mav.addObject("propertyTypes", categoriesTypes);
        mav.addObject("locations", locationDtos);
        mav.addObject("searchCriteria", new SearchCriteria());

        return mav;
    }
    @PostMapping("/search-property")
    public ModelAndView getAllPropertiesBySearch(@ModelAttribute SearchCriteria searchCriteria) {
        return getModelAndView();
    }
    @GetMapping("/search-property")
    public ModelAndView getAllPropertiesByType(@RequestParam(required = false) String categoryType) {
        return getModelAndView();
    }
    private ModelAndView getModelAndView() {
        ModelAndView mav = new ModelAndView("realstate/frontend/property-list");
        List<PropertyDto> propertyEntityList = propertyService.getAllProperties();
        List<Pair<String,String>> carouselImages = portalService.getPortalCarouselImages();
        List<PropertyTypeDto> categoriesTypes = propertyService.getAllPropertyTypes();
        List<LocationDto> locationDtos = getLocations();
        SearchCriteria attributeValue = new SearchCriteria();

        mav.addObject("description", "Hola Mundo con ModelAndView");
        mav.addObject("carouselImages", carouselImages);
        mav.addObject("properties", propertyEntityList);
        mav.addObject("propertyTypes", categoriesTypes);
        mav.addObject("locations", locationDtos);
        mav.addObject("searchCriteria", attributeValue);
        return mav;
    }
    @GetMapping("/property/{id}")
    public ModelAndView getPropertyDetail(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {

        ModelAndView mav = new ModelAndView("realstate/frontend/property-detail");
        List<String> features = Arrays.asList(
                "Feature 1",
                "Feature 2",
                "Feature 3"
        );
        PropertyDto propertyById = propertyService.getPropertyById(id);
        PropertyDetailResponse pdr = propertyMapper.toDto(propertyById);
        pdr.setFeatures(features);
        try {
            mav.addObject("searchCriteria", new SearchCriteria());
            mav.addObject("property", pdr);
            ContactFormDto build = ContactFormDto.builder().build();
            getCaptcha(build);
            mav.addObject("form", build);
            return mav;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return mav;
        }
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

            PropertyEntity propertyEntity = propertyService.saveProperty(propertyDto);
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
    private List<LocationDto> getLocations() {
        List<LocationDto> locationDtoList = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = 0; i < 6; i++) {
            locationDtoList.add(LocationDto.builder()
                    .id(faker.number().randomNumber())
                    .name(faker.country().capital()).build());
        }
        return locationDtoList;
    }

}
