package com.javaweb.repository.impl;

import com.javaweb.builder.TourSearchBuilder;
import com.javaweb.repository.TourRepository;
import com.javaweb.repository.entity.TourEntity;
import com.javaweb.utils.NumberUtil;
import com.javaweb.utils.StringUtil;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class TourRepositoryImpl implements TourRepository {
    static final String DB_URL = "jdbc:mysql://localhost:3306/quanh";
    static final String USER = "root";
    static final String PASS = "12092001";

    public static void joinTable(TourSearchBuilder tourSearchBuilder, StringBuilder sql) {
        String type_name = tourSearchBuilder.getType_name();
//        if (StringUtil.checkString(type_name)) {
//            sql.append(" INNER JOIN tour_typetour c ON t.tour_id = c.tour_id  ");
//            sql.append(" INNER JOIN typetour d ON c.type_id = d.type_id ");
//
//        }


    }

    public static void queryNomal(TourSearchBuilder tourSearchBuilder, StringBuilder where) {

        try {
            Field[] fields = TourSearchBuilder.class.getDeclaredFields();
            for (Field item : fields) {
                item.setAccessible(true);
                String name = item.getName();
                if (!name.equals("type_name") && !name.equals("duration") && !name.equals("price")) {
                    String value = null;
                    if (item.get(tourSearchBuilder) != null) {
                        value = item.get(tourSearchBuilder).toString();
                    }
                    if (value != null) {
                        if (NumberUtil.isNumber(value)) {
                            where.append(" AND t." + name + " = " + value);
                        } else {
                            where.append(" AND t." + name + " like '%" + value + "%'");
                        }
                    }
                }
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
//        for (Map.Entry<String, Object> entry : params.entrySet()) {
//            if (!entry.getKey().equals("type_name") && !entry.getKey().equals("duration") && !entry.getKey().equals("price")) {
//                String value = (String) entry.getValue();
//                if (StringUtil.checkString(value)) {
//                    if (NumberUtil.isNumber(value)) {
//                        where.append(" AND t." + entry.getKey() + " = " + value);
//                    } else {
//                        where.append(" AND t." + entry.getKey() + " like '%" + value + "%'");
//                    }
//                }
//
//
//            }
//
//        }
    }

    public static void querySpecial(TourSearchBuilder tourSearchBuilder, StringBuilder where) {

        // 1. Xử lý type_name (có thể null)
        String type_name = tourSearchBuilder.getType_name();
        if (StringUtil.checkString(type_name)) {
            where.append(" AND EXISTS (");
            where.append("   SELECT 1 FROM tour_typetour c ");
            where.append("   INNER JOIN typetour d ON c.type_id = d.type_id ");
            where.append("   WHERE c.tour_id = t.tour_id ");
            where.append("     AND d.type_name LIKE '%").append(type_name.replace("'", "''")).append("%'");
            where.append(" )");
        }

        // 2. Xử lý price (tránh gọi .toString() trên null)????????????? sao co comment roi ma van goi toString tren null??????????????ddou

        Integer price = tourSearchBuilder.getPrice(); // sao price lai Interger thf tien cha inter
        if (price != null) {
            String stringPrice = price.toString();
            where.append(" AND t.price <= ").append(stringPrice);
        }

        // 3. Xử lý duration (kiểu chuỗi, tách danh sách)
        String durationStr = tourSearchBuilder.getDuration(); // ví dụ: "2 ngày,3 ngày"
        List<String> duration = durationStr != null && !durationStr.isEmpty()
                ? Arrays.asList(durationStr.split(","))
                : new ArrayList<>();

        if (!duration.isEmpty()) {
            List<String> quoted = duration.stream()
                    .map(s -> "'" + s.replace("'", "''") + "'")
                    .collect(Collectors.toList());
            where.append(" AND t.duration IN (").append(String.join(",", quoted)).append(")");
        }
    }

    @Override
    public List<TourEntity> findTour(TourSearchBuilder tourSearchBuilder) {
        List<TourEntity> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT t.* FROM tour t");
        StringBuilder where = new StringBuilder(" where 1=1 ");

        joinTable(tourSearchBuilder, sql);
        queryNomal(tourSearchBuilder, where);
        querySpecial(tourSearchBuilder, where);
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
