package com.javaweb.service.impl;

import com.javaweb.model.TourDTO;
import com.javaweb.model.poiDetailDTO;
import com.javaweb.repository.PoiDetailRespository;
import com.javaweb.repository.TourRepository;
import com.javaweb.repository.entity.PoiEntity;
import com.javaweb.repository.entity.TourEntity;
import com.javaweb.service.PoiDetailService;
import com.javaweb.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service




public class PoiDetailServiceImpl implements PoiDetailService {
    @Autowired
    private PoiDetailRespository poiDetailRespository;

    @Override
    public List<poiDetailDTO> findDetail(String tour_name) {
        List<PoiEntity> poiEntities = poiDetailRespository.findDetail(tour_name);
        List<poiDetailDTO> result = new ArrayList<>();

        for (PoiEntity entity : poiEntities) {
            poiDetailDTO dto = new poiDetailDTO();
            dto.setPoi_name(entity.getPoi_name());
            result.add(dto);
        }

        return result;
    }

}
