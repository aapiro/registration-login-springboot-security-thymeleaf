package com.ilimitech.webapp.realstate.mapper;

import com.ilimitech.webapp.realstate.dto.ImageDto;
import com.ilimitech.webapp.realstate.dto.PropertyDto;
import com.ilimitech.webapp.realstate.entity.PropertyEntity;
import com.ilimitech.webapp.realstate.dto.PropertyTypeDto;
import com.ilimitech.webapp.realstate.entity.PropertyTypeEntity;
import com.ilimitech.webapp.realstate.bff.PropertyDetailResponse;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface PropertyMapper {
    PropertyEntity toEntity(PropertyDto propertyDto);

    @AfterMapping
    default void linkImages(@MappingTarget PropertyEntity propertyEntity) {
        propertyEntity.getImageEntities().forEach(image -> image.setPropertyEntity(propertyEntity));
    }

    //    @Mapping(target = "ignoredField", ignore = true)
    PropertyDto toDto(PropertyEntity propertyEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PropertyEntity partialUpdate(PropertyDto propertyDto, @MappingTarget PropertyEntity propertyEntity);

    PropertyTypeEntity toEntity(PropertyTypeDto propertyTypeDto);

    PropertyTypeDto toDto(PropertyTypeEntity propertyType);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PropertyTypeEntity partialUpdate(PropertyTypeDto propertyTypeDto, @MappingTarget PropertyTypeEntity propertyType);

    //    PropertyDto toEntity(PropertyDetailResponse propertyDetailResponse);
    @Mapping(source = "propertyContact.email", target = "email")
    @Mapping(target = "images", expression = "java(imagesToStrings(propertyDto))")
    PropertyDetailResponse toDto(PropertyDto propertyDto);

    default List<String> imagesToStrings(PropertyDto propertyDto) {
        return propertyDto.getImageEntities().stream().map(ImageDto::getImageUrl).toList();
    }

    com.ilimitech.webapp.realstate.entity.PropertyTypeEntity toEntity(PropertyTypeEntity propertyType);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PropertyTypeEntity partialUpdate(PropertyTypeEntity propertyType, @MappingTarget PropertyTypeEntity propertyTypeEntity);

}