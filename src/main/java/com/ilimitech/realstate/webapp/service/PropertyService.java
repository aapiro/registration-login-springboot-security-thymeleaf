package com.ilimitech.realstate.webapp.service;

import com.ilimitech.realstate.administration.entity.User;
import com.ilimitech.realstate.administration.repository.UserRepository;
import com.ilimitech.realstate.webapp.dto.PropertyDto;
import com.ilimitech.realstate.webapp.dto.PropertyTypeDto;
import com.ilimitech.realstate.webapp.entity.PropertyEntity;
import com.ilimitech.realstate.webapp.entity.PropertyTypeEntity;
import com.ilimitech.realstate.webapp.entity.UserContactEntity;
import com.ilimitech.realstate.webapp.mapper.PropertyMapper;
import com.ilimitech.realstate.webapp.mapper.PropertyTypeMapper;
import com.ilimitech.realstate.webapp.repository.PropertyRepository;
import com.ilimitech.realstate.webapp.repository.PropertyTypeRepository;
import com.ilimitech.realstate.webapp.repository.UserContactRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final PropertyTypeRepository propertyTypeRepository;
    private final UserContactRepository userContactRepository;
    private final UserRepository userRepository;
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
    @Transactional
    public PropertyEntity saveProperty(PropertyDto propertyDto, UserDetails userDetails) {
        User user = userRepository.findByUserNameLikeIgnoreCase(userDetails.getUsername())
                .orElseThrow();

        PropertyEntity propertyEntity = propertyMapper.toEntity(propertyDto);

        UserContactEntity userContact = userContactRepository.findFirstByUser(user)
                .orElseGet(() -> userContactRepository.save(UserContactEntity.builder().user(user).build()));
//        propertyDto.setUserContact(userContactMapper.toDto(userContact));

        PropertyTypeEntity propertyType = propertyTypeRepository.findByNameIgnoreCase(propertyDto.getPropertyType().getName())
                .orElseGet(() -> propertyTypeRepository.save(propertyTypeMapper.toEntity(PropertyTypeDto.builder().name(propertyDto.getPropertyType().getName())
                        .build())));
//        propertyDto.setPropertyType(propertyTypeMapper.toDto(propertyTypeEntity));
        propertyEntity.setUserContact(userContact);
        propertyEntity.setPropertyType(propertyType);
        propertyEntity.setId(null);
        propertyEntity.setActive(true);
        return propertyRepository.save(propertyEntity);
    }

}
