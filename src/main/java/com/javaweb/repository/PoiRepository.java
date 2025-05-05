package com.javaweb.repository;

import com.javaweb.repository.entity.PoiEntity;

import java.util.List;

public interface PoiRepository {
    List<PoiEntity> findAll(String name, String address, List<Integer> poi_id);
    void DeleteById(Long id);
}
