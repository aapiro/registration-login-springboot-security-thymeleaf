package com.ilimitech.webapp.realstate.service;

import com.ilimitech.webapp.realstate.dto.PropertyDto;
import com.ilimitech.webapp.realstate.dto.PropertyTypeDto;
import com.ilimitech.webapp.realstate.entity.PropertyEntity;
import com.ilimitech.webapp.realstate.mapper.PropertyMapper;
import com.ilimitech.webapp.realstate.mapper.PropertyTypeMapper;
import com.ilimitech.webapp.realstate.repository.PropertyRepository;
import com.ilimitech.webapp.realstate.repository.PropertyTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PropertyService {

    private PropertyRepository propertyRepository;
    private PropertyTypeRepository propertyTypeRepository;
    private final PropertyMapper propertyMapper;
    private final PropertyTypeMapper propertyTypeMapper;

    public List<PropertyDto> getAllProperties() {
        return propertyRepository.findAll()
                .stream()
                .map(propertyMapper::toDto)
                .toList();
    }
    public PropertyDto getPropertyById(Long id) {
        Optional<PropertyEntity> byId = propertyRepository.findById(id);
        return byId
                .map(propertyMapper::toDto)
                .orElseGet(PropertyDto::new);
    }
    public PropertyEntity findPropertyById(Long id) {
        return propertyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Property not found with id: " + id));
    }
    public List<PropertyTypeDto> getAllPropertyTypes() {
        return propertyTypeRepository.findAll()
                .stream()
                .map(propertyTypeMapper::toDto)
                .toList();
    }
    public PropertyEntity saveProperty(PropertyDto propertyDto) {
        PropertyEntity propertyEntity = propertyMapper.toEntity(propertyDto);
        return propertyRepository.save(propertyEntity);
    }
}
