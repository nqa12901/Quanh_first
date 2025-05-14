package com.javaweb.repository.impl;

import com.javaweb.repository.TourRepository;
import com.javaweb.repository.entity.TourEntity;
import com.javaweb.utils.NumberUtil;
import com.javaweb.utils.StringUtil;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class TourRepositoryImpl implements TourRepository {
    static final String DB_URL = "jdbc:mysql://localhost:3306/quanh";
    static final String USER = "root";
    static final String PASS = "12092001";

    public static void joinTable(Map<String, Object> params, List<String> duration, StringBuilder sql) {
        String type_name = (String) params.get("type_name");
//        if (StringUtil.checkString(type_name)) {
//            sql.append(" INNER JOIN tour_typetour c ON t.tour_id = c.tour_id  ");
//            sql.append(" INNER JOIN typetour d ON c.type_id = d.type_id ");
//
//        }


    }

    public static void queryNomal(Map<String, Object> params, List<String> duration, StringBuilder where) {
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (!entry.getKey().equals("type_name") && !entry.getKey().equals("duration") && !entry.getKey().equals("price")) {
                String value = (String) entry.getValue();
                if (StringUtil.checkString(value)) {
                    if (NumberUtil.isNumber(value)) {
                        where.append(" AND t." + entry.getKey() + " = " + value);
                    } else {
                        where.append(" AND t." + entry.getKey() + " like '%" + value + "%'");
                    }
                }


            }

        }
    }

    public static void querySpecial(Map<String, Object> params, List<String> duration, StringBuilder where) {

        String type_name = (String) params.get("type_name");
//        if (StringUtil.checkString(type_name)) {
//            where.append(" AND d.type_name like '%" + type_name + "%'");
//
//        }
        if (StringUtil.checkString(type_name)) {
            where.append(" AND EXISTS (");
            where.append("   SELECT 1 FROM tour_typetour c ");
            where.append("   INNER JOIN typetour d ON c.type_id = d.type_id ");
            where.append("   WHERE c.tour_id = t.tour_id ");
            where.append("     AND d.type_name LIKE '%" + type_name + "%'");
            where.append(" )");
        }

        Long price = params.get("price") != null
                ? Long.valueOf(params.get("price").toString())
                : null;

        if (price != null) {
            where.append(" AND t.price <= " + price);
        }

        if (duration != null && !duration.isEmpty()) {
            List<String> quoted = duration.stream()
                    .map(s -> "'" + s + "'")
                    .collect(Collectors.toList());
            where.append(" AND t.duration IN (" + String.join(",", quoted) + ")");
        }

    }

    @Override
    public List<TourEntity> findTour(Map<String, Object> params, List<String> duration) {
        List<TourEntity> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT t.* FROM tour t");
        StringBuilder where = new StringBuilder(" where 1=1 ");

        joinTable(params, duration, sql);
        queryNomal(params, duration, where);
        querySpecial(params, duration, where);
        sql.append(where);


        System.out.println(sql.toString());
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {


            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    TourEntity tour = new TourEntity();

                    tour.setAddress(rs.getString("address"));
                    tour.setTour_name(rs.getString("tour_name"));
                    tour.setDescription(rs.getString("description"));
                    tour.setPrice(rs.getInt("price"));
                    tour.setDuration(rs.getString("duration"));
                    tour.setImage_url(rs.getString("image_url"));
                    tour.setRating(rs.getInt("rating"));


                    result.add(tour);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to connect to the database...");
        }

        return result;
    }

    @Override
    public void DeleteById(Long id) {
        // To do
    }
}
