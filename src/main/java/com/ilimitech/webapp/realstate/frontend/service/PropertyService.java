package com.ilimitech.webapp.realstate.frontend.service;

import com.ilimitech.webapp.realstate.entity.PropertyDto;
import com.ilimitech.webapp.realstate.entity.PropertyEntityMapper;
import com.ilimitech.webapp.realstate.entity.PropertyMapper;
import com.ilimitech.webapp.realstate.frontend.repository.PropertyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PropertyService {

    private PropertyRepository propertyRepository;
    private final PropertyMapper propertyMapper;
    private final PropertyEntityMapper propertyEntityMapper;

    public List<PropertyDto> getAllProperties() {

        return propertyRepository.findAll().stream().map(propertyMapper::toDto).collect(Collectors.toList());
        // En un escenario real, estos datos podrían venir de una base de datos reactiva.
//        return Flux.just(
//                PropertyBuilder.builder().id(1L).title("Casa Urbana para venta").area(7.0).address("ADRESS").baths(2).beds(3).type("Apartamento").imageName("property-1.jpg").price(1000.0).status("For Sell").build(),
//                PropertyBuilder.builder().id(2L).title("Casa Urbana para venta").area(7.0).address("ADRESS").baths(2).beds(3).type("Apartamento").imageName("property-2.jpg").price(1000.0).status("For Sell").build(),
//                PropertyBuilder.builder().id(3L).title("Casa Urbana para venta").area(7.0).address("ADRESS").baths(2).beds(3).type("Apartamento").imageName("property-3.jpg").price(1000.0).status("For Sell").build(),
//                PropertyBuilder.builder().id(4L).title("Casa Urbana para venta").area(7.0).address("ADRESS").baths(2).beds(3).type("Apartamento").imageName("property-4.jpg").price(1000.0).status("For Sell").build(),
//                PropertyBuilder.builder().id(5L).title("Casa Urbana para venta").area(7.0).address("ADRESS").baths(2).beds(3).type("Apartamento").imageName("property-5.jpg").price(1000.0).status("For Sell").build(),
//                PropertyBuilder.builder().id(6L).title("Casa Urbana para venta").area(7.0).address("ADRESS").baths(2).beds(3).type("Apartamento").imageName("property-6.jpg").price(1000.0).status("For Sell").build());
    }

    public PropertyDto getPropertyById(Long id) {
        return propertyRepository.findById(id)
                .map(propertyEntityMapper::toDto)
                .orElseGet(PropertyDto::new);
    }

//    public void save(PropertyFrontendEntity property) {
//        propertyRepository.save(property);
//    }
}
