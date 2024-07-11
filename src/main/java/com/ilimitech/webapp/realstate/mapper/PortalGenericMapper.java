package com.ilimitech.webapp.realstate.mapper;

import com.ilimitech.webapp.realstate.entity.PortalGenericImageEntity;
import com.ilimitech.webapp.realstate.dto.PortalGenericImageDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PortalGenericMapper {
    PortalGenericImageEntity toEntity(PortalGenericImageDto portalGenericImageDto);

    PortalGenericImageDto toDto(PortalGenericImageEntity portalGenericImageEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PortalGenericImageEntity partialUpdate(PortalGenericImageDto portalGenericImageDto, @MappingTarget PortalGenericImageEntity portalGenericImageEntity);
}