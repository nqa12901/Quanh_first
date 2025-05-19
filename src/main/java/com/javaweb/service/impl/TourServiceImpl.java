package com.javaweb.service.impl;

import com.javaweb.builder.TourSearchBuilder;
import com.javaweb.converter.TourDTOConverter;
import com.javaweb.converter.TourSearchBuilderConverter;
import com.javaweb.model.TourDTO;
import com.javaweb.repository.TourRepository;
import com.javaweb.repository.entity.TourEntity;
import com.javaweb.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TourServiceImpl implements TourService {
    @Autowired
    private TourRepository TourRepository;
    @Autowired
    private TourDTOConverter tourDTOConverter;
    @Autowired
    private TourSearchBuilderConverter tourSearchBuilderConverter;
    @Override
    public List<TourDTO> findTour(Map<String, Object> params,List<String> duration) {
        TourSearchBuilder tourSearchBuilder=tourSearchBuilderConverter.toTourSearchBuilder( params,duration);
        List<TourEntity> tourEntities = TourRepository.findTour(tourSearchBuilder);
        List<TourDTO> result = new ArrayList<>();

        for (TourEntity tourEntity : tourEntities) {
            TourDTO dto =tourDTOConverter.toTourDTO(tourEntity);
            result.add(dto);
        }

        return result;
    }

}
