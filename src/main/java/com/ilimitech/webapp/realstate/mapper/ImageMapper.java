package com.ilimitech.webapp.realstate.mapper;

import com.ilimitech.webapp.realstate.dto.ImageDto;
import com.ilimitech.webapp.realstate.entity.ImageEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ImageMapper {
    ImageEntity toEntity(ImageDto imageDto);

    ImageDto toDto(ImageEntity imageEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ImageEntity partialUpdate(ImageDto imageDto, @MappingTarget ImageEntity imageEntity);
}