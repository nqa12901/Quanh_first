package com.javaweb.converter;

import com.javaweb.model.TourDTO;
import com.javaweb.repository.entity.TourEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class TourDTOConverter {
    @Autowired
    private ModelMapper modelMapper;
    public TourDTO toTourDTO(TourEntity tourEntity) {
        TourDTO dto = modelMapper.map(tourEntity,TourDTO.class);

//        dto.setTour_name(tourEntity.getTour_name());
//        dto.setDescription(tourEntity.getDescription());
//        dto.setAddress(tourEntity.getAddress());
//        dto.setPrice(tourEntity.getPrice());
//        dto.setDuration(tourEntity.getDuration());
//        dto.setImage_url(tourEntity.getImage_url());
//        dto.setRating(tourEntity.getRating());
        return dto;

    }
}
