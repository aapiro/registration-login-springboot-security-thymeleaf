package com.ilimitech.webapp.realstate.mapper;

import com.ilimitech.webapp.realstate.entity.GenericImageEntity;
import com.ilimitech.webapp.realstate.dto.PortalGenericImageDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PortalGenericMapper {
    GenericImageEntity toEntity(PortalGenericImageDto portalGenericImageDto);

    PortalGenericImageDto toDto(GenericImageEntity genericImageEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    GenericImageEntity partialUpdate(PortalGenericImageDto portalGenericImageDto, @MappingTarget GenericImageEntity genericImageEntity);
}