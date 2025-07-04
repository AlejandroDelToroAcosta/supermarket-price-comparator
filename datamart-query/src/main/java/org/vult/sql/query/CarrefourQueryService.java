package org.vult.sql.query;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarrefourQueryService {
    private final String dbPath;

    public CarrefourQueryService(String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:" + dbPath);
    }

    // 1. Buscar productos por nombre (Carrefour)
    public List<String> searchCarrefourByName(String keyword) throws SQLException {
        String sql = """
            SELECT name, unit_price FROM carrefour_products
            WHERE LOWER(name) LIKE ?
        """;
        List<String> results = new ArrayList<>();

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + keyword.toLowerCase() + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                results.add(rs.getString("name") + " - " + rs.getDouble("unit_price") + " €");
            }
        }
        return results;
    }

}
