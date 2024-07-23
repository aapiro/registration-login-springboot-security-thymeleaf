package com.ilimitech.realstate.webapp.controller;

import com.github.javafaker.Faker;
import com.ilimitech.realstate.administration.service.UserSecurityService;
import com.ilimitech.realstate.webapp.bff.PropertyDetailResponse;
import com.ilimitech.realstate.webapp.bff.PropertyFormRequest;
import com.ilimitech.realstate.webapp.dto.ContactFormDto;
import com.ilimitech.realstate.webapp.dto.LocationDto;
import com.ilimitech.realstate.webapp.dto.PropertyDto;
import com.ilimitech.realstate.webapp.dto.PropertyTypeDto;
import com.ilimitech.realstate.webapp.entity.PropertyEntity;
import com.ilimitech.realstate.webapp.mapper.PropertyMapper;
import com.ilimitech.realstate.webapp.service.PortalService;
import com.ilimitech.realstate.webapp.service.PropertyService;
import com.ilimitech.realstate.webapp.util.Pair;
import com.ilimitech.realstate.webapp.util.SearchCriteria;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.ilimitech.realstate.administration.util.CaptchaUtil.getCaptcha;

@Slf4j
@Controller
@AllArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;
    private final PortalService portalService;
    private final PropertyMapper propertyMapper;
    private final UserSecurityService userSecurityService;

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
    public ModelAndView getFormForAddProperty() {

        ModelAndView mav = new ModelAndView("realstate/dashboard/property-form");
        List<String> propertyTypeOptions = Arrays.asList("Option 1", "Option 2", "Option 3");
        PropertyFormRequest propertyReq = PropertyFormRequest.builder().build();
        mav.addObject("propertyReq", propertyReq);
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
    public ModelAndView saveProperty(@Valid @ModelAttribute("propertyReq") PropertyFormRequest propertyFormRequest,
            BindingResult result) {

        if (result.hasErrors()) {
            log.error("ERROR LIST: {}", result.getAllErrors());
            ModelAndView mav = this.getFormForAddProperty();
            mav.addObject("propertyReq", propertyFormRequest);
            return mav;
        }
        ModelAndView mav = new ModelAndView("realstate/dashboard/images");
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Object principal = authentication.getPrincipal();
            UserDetails userDetails = (UserDetails) principal;

            PropertyDto dto = propertyMapper.toDto(propertyFormRequest);
            PropertyEntity propertyEntity = propertyService.createProperty(dto, userDetails);
            String imageServerUrl = "/images";
            String userId = String.valueOf(propertyEntity.getUserContact().getUser().getId());
            String itemId = String.valueOf(propertyEntity.getId());
            mav.addObject("userId", userId);
            mav.addObject("itemId", itemId);
            mav.addObject("imageServerUrl",imageServerUrl);
        } catch (Exception e) {
            log.error(e.toString());
        }

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
