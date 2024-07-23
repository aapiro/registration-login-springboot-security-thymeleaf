package com.ilimitech.realstate.webapp.mapper;

import com.ilimitech.realstate.webapp.bff.PropertyFormRequest;
import com.ilimitech.realstate.webapp.dto.ImageDto;
import com.ilimitech.realstate.webapp.dto.PropertyDto;
import com.ilimitech.realstate.webapp.entity.FeatureEntity;
import com.ilimitech.realstate.webapp.entity.ImageEntity;
import com.ilimitech.realstate.webapp.entity.PropertyEntity;
import com.ilimitech.realstate.webapp.dto.PropertyTypeDto;
import com.ilimitech.realstate.webapp.entity.PropertyTypeEntity;
import com.ilimitech.realstate.webapp.bff.PropertyDetailResponse;
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

    @Mapping(target = "features", expression = "java(buildPropertyFeatures(dto))")
//    @Mapping(target = "propertyType" ,ignore = true)
    PropertyEntity toEntity(PropertyDto dto);

    default FeatureEntity buildPropertyFeatures(PropertyDto dto) {
        return FeatureEntity.builder()
                .airConditioning(dto.isAirConditioning())
                .balcony(dto.isBalcony())
                .cellphone(dto.isCellphone())
                .chat(dto.isChat())
                .email(dto.isEmail())
                .greenArea(dto.isGreenArea())
                .orientationEast(dto.isOrientationEast())
                .orientationNorth(dto.isOrientationNorth())
                .orientationSouth(dto.isOrientationSouth())
                .orientationWest(dto.isOrientationWest())
                .parkingPlace(dto.isParkingPlace())
                .pool(dto.isPool())
                .storageRoom(dto.isStorageRoom())
                .wardrobes(dto.isWardrobes())
                .whatsapp(dto.isWhatsapp())
                .build();
    }
    @AfterMapping
    default void linkImages(@MappingTarget PropertyEntity propertyEntity) {
        propertyEntity.getImageEntities().forEach(image -> image.setPropertyEntity(propertyEntity));
    }

    //    @Mapping(target = "ignoredField", ignore = true)
    @Mapping(target = "frontPage", expression = "java(getFrontPageImage(propertyEntity))")
    PropertyDto toDto(PropertyEntity propertyEntity);

    default ImageDto getFrontPageImage(PropertyEntity propertyEntity) {
        ImageEntity imageEntity = propertyEntity.getImageEntities().stream().filter(ImageEntity::isFrontPage).findFirst()
                .orElse(ImageEntity.builder().build());
        return ImageDto.builder().id(imageEntity.getId()).name(imageEntity.getName()).imageUrl(imageEntity.getImageUrl()).build();
    }
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PropertyEntity partialUpdate(PropertyDto propertyDto, @MappingTarget PropertyEntity propertyEntity);

    PropertyTypeEntity toEntity(PropertyTypeDto propertyTypeDto);

    PropertyTypeDto toDto(PropertyTypeEntity propertyType);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PropertyTypeEntity partialUpdate(PropertyTypeDto propertyTypeDto, @MappingTarget PropertyTypeEntity propertyType);

    //    PropertyDto toEntity(PropertyDetailResponse propertyDetailResponse);
    @Mapping(source = "userContact.email", target = "email")
    @Mapping(target = "images", expression = "java(imagesToStrings(propertyDto))")
    PropertyDetailResponse toDto(PropertyDto propertyDto);

    default List<String> imagesToStrings(PropertyDto propertyDto) {
        return propertyDto.getImageEntities().stream().map(ImageDto::getImageUrl).toList();
    }

    com.ilimitech.realstate.webapp.entity.PropertyTypeEntity toEntity(PropertyTypeEntity propertyType);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PropertyTypeEntity partialUpdate(PropertyTypeEntity propertyType, @MappingTarget PropertyTypeEntity propertyTypeEntity);

    /**
     *
     * @param propertyFormRequest
     * @return
     */
    @Mapping(target = "propertyType.name", source = "propertyType")
    PropertyDto toDto(PropertyFormRequest propertyFormRequest);

    @Mapping(target = "propertyType", source = "propertyType.name")
    PropertyFormRequest toFormRequest(PropertyDto propertyDto);

    @Mapping(target = "propertyType.name", source = "propertyType")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PropertyDto partialUpdate(PropertyFormRequest propertyFormRequest, @MappingTarget PropertyDto propertyDto);

}