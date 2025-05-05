package com.javaweb.service;

import com.javaweb.model.poiDTO;
import com.javaweb.repository.entity.PoiEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PoiService {
    List<poiDTO> findAll(String name,String address, List<Integer> poi_id);
}
