package com.javaweb.repository;

import com.javaweb.repository.entity.PoiEntity;
import com.javaweb.repository.entity.TourEntity;

import java.util.List;
import java.util.Map;

public interface PoiDetailRespository {
    List<PoiEntity> findDetail(String tour_name);

}
