package com.javaweb.service;

import com.javaweb.model.TourDTO;

import java.util.List;
import java.util.Map;


public interface TourService {
    List<TourDTO> findTour(Map<String,Object> params,List<String> duration);
}

