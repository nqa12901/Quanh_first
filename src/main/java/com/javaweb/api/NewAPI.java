package com.javaweb.api;

import com.javaweb.model.TourDTO;
import com.javaweb.model.poiDTO;
import com.javaweb.model.poiDetailDTO;
import com.javaweb.model.testDTO;
import com.javaweb.customexception.RequiredException;
import com.javaweb.repository.PoiDetailRespository;
import com.javaweb.service.PoiDetailService;
import com.javaweb.service.TourService;
import com.javaweb.service.impl.PoiDetailServiceImpl;
import com.javaweb.service.impl.TourServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//map tim kiem get - post body them
@RestController
public class NewAPI {
    @Autowired
    private TourService TourService;
    @Autowired
    private TourServiceImpl poiServiceImpl;

    @GetMapping(value = "api/tour")
    public List<TourDTO> listtour(@RequestParam Map<String, Object> params,
                                  @RequestParam(name = "duration", required = false) List<String> duration) {
        List<TourDTO> listTour = TourService.findTour(params, duration);
        return listTour;
    }

    @Autowired
    private PoiDetailService PoiDetailService;
    @Autowired
    private PoiDetailServiceImpl PoiDetailServiceImpl;

    @GetMapping(value = "api/poioftour")
    public List<poiDetailDTO> listpoioftour(@RequestParam(value = "tour_name") String tour_name) {
        List<poiDetailDTO> listPoioftour = PoiDetailService.findDetail(tour_name);
        return listPoioftour;
    }

    //EXCEPTION
    public void checkName(testDTO testDTO) {
        if (testDTO.getName() == null) {
            throw new RequiredException("quanh null");
        } else if (testDTO.getName().equals("quanh")) {
            throw new RequiredException("quanh xinh vai o");
        }
    }
}

//    @GetMapping(value = "api/quanh")
//    public List<poiDTO> quanh(@RequestParam Map<String, Object> params,
//                              @RequestParam (value="type_id",required = false) List<Integer> type_id) {
//        List<poiDTO> result = PoiService.findAll(params,type_id);
//        return result;
//    }
