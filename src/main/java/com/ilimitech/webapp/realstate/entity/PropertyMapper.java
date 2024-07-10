package com.ilimitech.webapp.realstate.entity;

import com.ilimitech.webapp.realstate.frontend.bff.PropertyDetailResponse;
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

    PropertyType toEntity(PropertyTypeDto propertyTypeDto);

    PropertyTypeDto toDto(PropertyType propertyType);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PropertyType partialUpdate(PropertyTypeDto propertyTypeDto, @MappingTarget PropertyType propertyType);

//    PropertyDto toEntity(PropertyDetailResponse propertyDetailResponse);
    @Mapping(source = "propertyContact.email",target = "email")
    @Mapping(target = "images", expression = "java(imagesToStrings(propertyDto))")
    PropertyDetailResponse toDto(PropertyDto propertyDto);

    default List<String> imagesToStrings(PropertyDto propertyDto) {
        return propertyDto.getImageEntities().stream().map(ImageDto::getImageUrl).toList();
    }

//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    PropertyDto partialUpdate(PropertyDetailResponse propertyDetailResponse, @MappingTarget PropertyDto propertyDto);
}