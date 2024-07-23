package com.ilimitech.realstate.webapp.mapper;

import com.ilimitech.realstate.webapp.dto.UserContactDto;
import com.ilimitech.realstate.webapp.entity.UserContactEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserContactMapper {
    UserContactEntity toEntity(UserContactDto userContactDto);

    UserContactDto toDto(UserContactEntity userContactEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserContactEntity partialUpdate(UserContactDto userContactDto, @MappingTarget UserContactEntity userContactEntity);
}