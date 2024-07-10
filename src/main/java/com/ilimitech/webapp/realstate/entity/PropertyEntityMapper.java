package com.ilimitech.webapp.realstate.entity;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PropertyEntityMapper {

    PropertyEntity toEntity(PropertyDto propertyDto);

    PropertyDto toDto(PropertyEntity propertyEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PropertyEntity partialUpdate(PropertyDto propertyDto, @MappingTarget PropertyEntity propertyEntity);

}