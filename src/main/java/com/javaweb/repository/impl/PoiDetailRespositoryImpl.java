package com.javaweb.repository.impl;

import com.javaweb.repository.PoiDetailRespository;
import com.javaweb.repository.entity.PoiEntity;
import com.javaweb.repository.entity.TourEntity;
import com.javaweb.utils.StringUtil;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository

public class PoiDetailRespositoryImpl implements PoiDetailRespository {
    static final String DB_URL = "jdbc:mysql://localhost:3306/quanh";
    static final String USER = "root";
    static final String PASS = "12092001";

    public static void joinTable(String tour_name, StringBuilder sql) {
        if (StringUtil.checkString(tour_name)) {
            sql.append(" INNER JOIN tour_poi c ON t.tour_id = c.tour_id  ");
            sql.append(" INNER JOIN poi d ON c.poi_id = d.poi_id ");

        }


    }

    @Override
    public List<PoiEntity> findDetail(String tour_name) {
        List<PoiEntity> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT t.* ,d.poi_name FROM tour t ");
        StringBuilder where = new StringBuilder(" where  t.tour_name= '"+tour_name +"' order by c.order_in_tour ASC ");
        joinTable(tour_name, sql);
        sql.append(where);


        System.out.println(sql);

        System.out.println(sql.toString());
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {


            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    PoiEntity poi = new PoiEntity();


                    poi.setPoi_name(rs.getString("poi_name"));


                    result.add(poi);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to connect to the database...");
        }

        return result;
    }


}
