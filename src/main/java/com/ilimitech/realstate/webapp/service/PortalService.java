package com.ilimitech.realstate.webapp.service;

import com.ilimitech.realstate.webapp.mapper.PortalGenericMapper;
import com.ilimitech.realstate.webapp.repository.PortalGenericImageRepository;
import com.ilimitech.realstate.webapp.util.Pair;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PortalService {

    private PortalGenericImageRepository portalGenericImageRepository;
    private final PortalGenericMapper portalGenericMapper;

    public List<Pair<String,String>> getPortalCarouselImages() {
        return portalGenericImageRepository.findAll()
                .stream()
                .map(portalGenericMapper::toDto)
                .map(dto -> new Pair<>(dto.url(),dto.name()))
                .toList();
    }
}
