package com.javaweb.service.impl;

import com.javaweb.model.poiDTO;
import com.javaweb.repository.PoiRepository;
import com.javaweb.repository.entity.PoiEntity;
import com.javaweb.service.PoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PoiServiceImpl implements PoiService {
    @Autowired
    private PoiRepository PoiRepository;

    @Override
    public List<poiDTO> findAll(String name,String address,List<Integer> poi_id) {
        List<PoiEntity> poiEntities = PoiRepository.findAll(name,address,poi_id);
        List<poiDTO> result = new ArrayList<>();

        for (PoiEntity poiEntity : poiEntities) {
            poiDTO dto = new poiDTO();
            dto.setPoi_id(poiEntity.getPoi_id());
            dto.setAddress(poiEntity.getAddress());
            dto.setName(poiEntity.getName());
            dto.setType_id(poiEntity.getType_id());

            result.add(dto);
        }

        return result;
    }
}
