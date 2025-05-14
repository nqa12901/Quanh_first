package com.javaweb.repository;

import com.javaweb.repository.entity.TourEntity;

import java.util.List;
import java.util.Map;

public interface TourRepository {
    List<TourEntity> findTour( Map<String,Object> params,List<String> duration);
    void DeleteById(Long id);
}
