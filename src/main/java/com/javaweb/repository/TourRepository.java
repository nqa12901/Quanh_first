package com.javaweb.repository;

import com.javaweb.builder.TourSearchBuilder;
import com.javaweb.repository.entity.TourEntity;

import java.util.List;
import java.util.Map;

public interface TourRepository {
    List<TourEntity> findTour( TourSearchBuilder tourSearchBuilder);
    void DeleteById(Long id);
}
