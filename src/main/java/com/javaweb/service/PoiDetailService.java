package com.javaweb.service;

import com.javaweb.model.poiDTO;
import com.javaweb.model.poiDetailDTO;

import java.util.List;

public interface PoiDetailService {
    List<poiDetailDTO> findDetail(String tour_name) ;
}
