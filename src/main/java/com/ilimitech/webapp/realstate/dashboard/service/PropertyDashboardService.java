package com.ilimitech.webapp.realstate.dashboard.service;

import com.ilimitech.webapp.realstate.entity.PropertyDto;
import com.ilimitech.webapp.realstate.entity.PropertyEntity;
import com.ilimitech.webapp.realstate.entity.PropertyMapper;
import com.ilimitech.webapp.realstate.frontend.repository.PropertyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PropertyDashboardService {

    private final PropertyRepository propertyRepository;
    private final PropertyMapper mapper;

    public PropertyEntity saveProperty(PropertyDto propertyDto) {
        PropertyEntity propertyEntity = mapper.toEntity(propertyDto);
        return propertyRepository.save(propertyEntity);
    }
}
