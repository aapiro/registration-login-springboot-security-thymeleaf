package com.ilimitech.webapp.realstate.mapper;

import com.ilimitech.webapp.realstate.dto.ContactFormDto;
import com.ilimitech.webapp.realstate.entity.ContactFormEntity;
import com.ilimitech.webapp.realstate.entity.PropertyEntity;
import com.ilimitech.webapp.realstate.service.PropertyService;
import org.mapstruct.BeanMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ContactFormMapper {


    @Mapping(source = "propertyId", target = "propertyEntity")
    ContactFormEntity toEntity(ContactFormDto dto, @Context PropertyService propertyService);

    @Mapping(source = "propertyEntity.id", target = "propertyId")
    ContactFormDto toDto(ContactFormEntity entity);

    default PropertyEntity map(String propertyId, @Context PropertyService propertyService) {
        return propertyService.findPropertyById(Long.parseLong(propertyId));
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ContactFormEntity partialUpdate(ContactFormDto contactFormDto, @MappingTarget ContactFormEntity contactFormEntity);
}