package com.ilimitech.webapp.realstate.entity;

import com.ilimitech.webapp.realstate.frontend.dto.ContactFormDto;
import com.ilimitech.webapp.realstate.frontend.service.PropertyService;
import org.mapstruct.BeanMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ContactFormEntityMapper {


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