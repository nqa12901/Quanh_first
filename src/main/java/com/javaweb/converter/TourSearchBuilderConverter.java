package com.javaweb.converter;

import com.javaweb.builder.TourSearchBuilder;
import com.javaweb.utils.MapUtil;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
// thong bao day la bean
@Component
public class TourSearchBuilderConverter {
    public TourSearchBuilder toTourSearchBuilder(Map<String, Object> params, List<String> duration) {
        TourSearchBuilder tourSearchBuilder = new TourSearchBuilder.Builder()
                .setType_name(MapUtil.getObject(params, "name", String.class))
                .setAddress(MapUtil.getObject(params, "address", String.class))
                .setPrice(MapUtil.getObject(params, "price", Integer.class))
                .setDuration(MapUtil.getObject(params, "duration", String.class))
                .setRating(MapUtil.getObject(params, "rating", Integer.class))
                .build();

        return tourSearchBuilder;
    }
}
