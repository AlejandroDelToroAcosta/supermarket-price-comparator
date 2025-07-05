package org.vult.sql.query;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CarrefourQueryService {
    private final String dbPath;

    public CarrefourQueryService(String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:" + dbPath);
    }

    // 🔍 Buscar productos por nombre
    public List<String> searchCarrefourByName(String keyword) throws SQLException {
        List<String> results = new ArrayList<>();
        String sql = """
            SELECT name, unit_price FROM carrefour_products
            WHERE LOWER(name) LIKE ?
        """;

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

    // 📂 Obtener categorías únicas entre los productos encontrados
    public Set<String> getCategoriesForNameSearch_Carrefour(String keyword) throws SQLException {
        Set<String> categories = new HashSet<>();
        String sql = """
            SELECT DISTINCT category FROM carrefour_products
            WHERE LOWER(name) LIKE ?
        """;

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + keyword.toLowerCase() + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                categories.add(rs.getString("category"));
            }
        }

        return categories;
    }

    // 🔍 Buscar productos por nombre y categoría
    public List<String> searchCarrefourByNameAndCategory(String keyword, String category) throws SQLException {
        List<String> results = new ArrayList<>();
        String sql = """
            SELECT name, unit_price FROM carrefour_products
            WHERE LOWER(name) LIKE ? AND category = ?
        """;

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + keyword.toLowerCase() + "%");
            pstmt.setString(2, category);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                results.add(rs.getString("name") + " - " + rs.getDouble("unit_price") + " €");
            }
        }

        return results;
    }
}
