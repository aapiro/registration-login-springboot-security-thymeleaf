package com.ilimitech.webapp.realstate.mapper;

import com.ilimitech.webapp.realstate.dto.PropertyTypeDto;
import com.ilimitech.webapp.realstate.entity.PropertyTypeEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PropertyTypeMapper {
    PropertyTypeEntity toEntity(PropertyTypeDto propertyTypeDto);

    PropertyTypeDto toDto(PropertyTypeEntity propertyTypeEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PropertyTypeEntity partialUpdate(PropertyTypeDto propertyTypeDto, @MappingTarget PropertyTypeEntity propertyTypeEntity);
}