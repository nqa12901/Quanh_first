package com.javaweb.repository.impl;

import com.javaweb.repository.PoiRepository;
import com.javaweb.repository.entity.PoiEntity;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class PoiRepositoryImpl implements PoiRepository {
    static final String DB_URL = "jdbc:mysql://localhost:3306/quanh";
    static final String USER = "root";
    static final String PASS = "12092001";

    @Override
    public List<PoiEntity> findAll(String name, String address, List<Integer> poi_id) {
        StringBuilder sql = new StringBuilder("SELECT * FROM poi a WHERE 1=1");
        List<Object> parameters = new ArrayList<>();

        if (name != null && !name.trim().isEmpty()) {
            sql.append(" AND a.name LIKE ?");
            parameters.add("%" + name + "%");
        }
        if (address != null && !address.trim().isEmpty()) {
            sql.append(" AND a.address LIKE ?");
            parameters.add("%" + address + "%");
        }
        if (poi_id != null && !poi_id.isEmpty()) {
            sql.append(" AND a.poi_id IN (");
            sql.append(String.join(",", Collections.nCopies(poi_id.size(), "?")));
            sql.append(")");
            parameters.addAll(poi_id);
        }

        List<PoiEntity> result = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            // Gán giá trị cho các tham số
            for (int i = 0; i < parameters.size(); i++) {
                stmt.setObject(i + 1, parameters.get(i));
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    PoiEntity entity = new PoiEntity();
                    entity.setPoi_id(rs.getInt("poi_id"));
                    entity.setName(rs.getString("name"));
                    entity.setType_id(rs.getInt("type_id"));
                    entity.setAddress(rs.getString("address"));
                    result.add(entity);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to connect to the database...");
        }

        return result;
    }

    @Override
    public   void DeleteById(Long id){


    }


}
