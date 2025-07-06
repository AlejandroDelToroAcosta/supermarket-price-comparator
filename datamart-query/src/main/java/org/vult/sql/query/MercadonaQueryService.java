package org.vult.sql.query;

import org.vult.sql.model.ProductDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MercadonaQueryService {
    private final String dbPath;

    public MercadonaQueryService(String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:" + dbPath);
    }
    public Set<String> getCategoriesForNameSearch_Mercadona(String keyword) throws SQLException {
        String sql = """
        SELECT DISTINCT category_name FROM mercadona_products
        WHERE LOWER(name) LIKE ?
    """;

        Set<String> categories = new HashSet<>();

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + keyword.toLowerCase() + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                categories.add(rs.getString("category_name"));
            }
        }
        return categories;
    }
    public List<ProductDTO> searchMercadonaByNameAndCategory(String keyword, String category) throws SQLException {
        String sql = """
        SELECT name, unit_price FROM mercadona_products
        WHERE LOWER(name) LIKE ? AND category_name = ?
    """;
        List<ProductDTO> results = new ArrayList<>();


        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + keyword.toLowerCase() + "%");
            pstmt.setString(2, category);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                double price = rs.getDouble("unit_price");
                results.add(new ProductDTO(name, price));
            }
        }
        return results;
    }
    // 3. Buscar productos por nombre (Mercadona)
    public List<ProductDTO> searchMercadonaProduct(String keyword) throws SQLException {
        List<ProductDTO> results = new ArrayList<>();
        String sql = "SELECT name, unit_price FROM mercadona_products WHERE name LIKE ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + keyword + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                double price = rs.getDouble("unit_price");
                results.add(new ProductDTO(name, price));
            }
        }

        return results;
    }


}
