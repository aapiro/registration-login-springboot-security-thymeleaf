package com.ilimitech.webapp.realstate.frontend.controller;

import com.github.javafaker.Faker;
import com.ilimitech.util.SearchCriteria;
import com.ilimitech.webapp.realstate.entity.Location;
import com.ilimitech.webapp.realstate.entity.PropertyDto;
import com.ilimitech.webapp.realstate.entity.PropertyMapper;
import com.ilimitech.webapp.realstate.entity.PropertyType;
import com.ilimitech.webapp.realstate.entity.PropertyTypeDto;
import com.ilimitech.webapp.realstate.frontend.bff.PropertyDetailResponse;
import com.ilimitech.webapp.realstate.frontend.service.PropertyService;
import lombok.AllArgsConstructor;
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
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class PropertyController {

    private PropertyService propertyService;
    private final PropertyMapper propertyMapper;

    @GetMapping("/index-properties")
    public ModelAndView getAllProperties() {
        ModelAndView mav = new ModelAndView("realstate/frontend/index");

//        IReactiveDataDriverContextVariable reactiveDataDrivenMode =
//                new ReactiveDataDriverContextVariable(propertyService.getAllProperties(), 1);
//        mav.addObject("properties", reactiveDataDrivenMode);

        List<PropertyDto> propertyEntityList = propertyService.getAllProperties();
        List<String> carouselImages = getCarouselImages();
        List<PropertyTypeDto> categoriesTypes = getCategoriesTypes().stream().map(propertyMapper::toDto).collect(Collectors.toList());
        List<Location> locations = getLocations();

        mav.addObject("description", "Hola Mundo con ModelAndView");
        mav.addObject("aboutTitle", "#1 Para encontrar tu lugar especial");
        mav.addObject("aboutDescription", "#1 Para encontrar tu lugar especial");
        mav.addObject("properties", propertyEntityList);
        mav.addObject("carouselImages", carouselImages);
        mav.addObject("categories", categoriesTypes);
        mav.addObject("propertyTypes", categoriesTypes);
        mav.addObject("locations", locations);
        mav.addObject("searchCriteria", new SearchCriteria());
        return mav;
    }
//    @GetMapping("/properties/new")
//    public ModelAndView addProperty() {
//        ModelAndView mav = new ModelAndView("dashboard/property-simple-form");
//        PropertyEntity PropertyEntity = new PropertyEntity();
//        mav.addObject("property", PropertyEntity);
//        mav.addObject("pageTitle", "Create new PropertyEntity");
//        return mav;
//    }

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
        List<String> carouselImages = getCarouselImages();
        List<PropertyType> categoriesTypes = getCategoriesTypes();
        List<Location> locations = getLocations();
        SearchCriteria attributeValue = new SearchCriteria();

        mav.addObject("description", "Hola Mundo con ModelAndView");
        mav.addObject("carouselImages", carouselImages);
        mav.addObject("properties", propertyEntityList);
        mav.addObject("propertyTypes", categoriesTypes);
        mav.addObject("locations", locations);
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
        PropertyDetailResponse pdr = propertyMapper.toDto(propertyService.getPropertyById(id));
        pdr.setFeatures(features);
        try {
            mav.addObject("searchCriteria", new SearchCriteria());
            mav.addObject("property", pdr);
            return mav;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());

            return mav;
        }
    }
    private List<Location> getLocations() {
        List<Location> locationList = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = 0; i < 6; i++) {
            locationList.add(Location.builder()
                    .id(faker.number().randomNumber())
                    .name(faker.country().capital()).build());
        }
        return locationList;
    }
    private List<PropertyType> getCategoriesTypes() {
        List<PropertyType> typeList = new ArrayList<>();
        typeList.add(PropertyType.builder()
                        .id(1)
                .type("Apartamento")
                .name("Apartamento")
                .icon("icon-apartment.png")
                .count(123)
                .delay(Math.random() * 0.8 + 0.1)
                .build());
        typeList.add(PropertyType.builder()
                        .id(2)
                .type("Villa")
                .name("Villa")
                .icon("icon-villa.png")
                .count(123)
                .delay(Math.random() * 0.8 + 0.1)
                .build());
        typeList.add(PropertyType.builder()
                .id(3)
                .type("Hogar")
                .name("Hogar")
                .icon("icon-house.png")
                .count(123)
                .delay(Math.random() * 0.8 + 0.1)
                .build());
        typeList.add(PropertyType.builder()
                .id(4)
                .type("Office")
                .name("Office")
                .icon("icon-housing.png")
                .count(123)
                .delay(Math.random() * 0.8 + 0.1)
                .build());
        typeList.add(PropertyType.builder()
                .id(5)
                .type("Building")
                .name("Building")
                .icon("icon-building.png")
                .count(123)
                .delay(Math.random() * 0.8 + 0.1)
                .build());
        typeList.add(PropertyType.builder()
                .id(6)
                .type("Townhouse")
                .name("Townhouse")
                .icon("icon-neighborhood.png")
                .count(123)
                .delay(Math.random() * 0.8 + 0.1)
                .build());
        typeList.add(PropertyType.builder()
                .id(7)
                .type("Shop")
                .name("Shop")
                .icon("icon-condominium.png")
                .count(123)
                .delay(Math.random() * 0.8 + 0.1)
                .build());
        typeList.add(PropertyType.builder()
                .id(8)
                .type("Garage")
                .name("Garage")
                .icon("icon-luxury.png")
                .count(123)
                .delay(Math.random() * 0.8 + 0.1)
                .build());
        return typeList;
    }
    private static List<String> getCarouselImages() {
        List<String> carouselImages = new ArrayList<>();
        carouselImages.add("carousel-1.jpg");
        carouselImages.add("carousel-2.jpg");
        return carouselImages;
    }
}
