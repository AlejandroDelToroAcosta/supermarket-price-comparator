package org.vult.sql.query;

import org.vult.sql.model.ProductDTO;

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

    public List<ProductDTO> searchCarrefourByName(String keyword) throws SQLException {
        List<ProductDTO> results = new ArrayList<>();
        String sql = "SELECT name, unit_price, image_url, supermarket FROM carrefour_products WHERE name LIKE ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + keyword + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                results.add(new ProductDTO(rs.getString("name"),
                        rs.getDouble("unit_price"), rs.getString("supermarket"),
                        rs.getString("image_url")));
            }
        }
        return results;
    }

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

    public List<ProductDTO> searchCarrefourByNameAndCategory(String keyword, String category) throws SQLException {
        List<ProductDTO> results = new ArrayList<>();
        String sql = """
            SELECT name, unit_price, supermarket, image_url FROM carrefour_products
            WHERE LOWER(name) LIKE ? AND category = ?
        """;

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + keyword.toLowerCase() + "%");
            pstmt.setString(2, category);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                results.add(new ProductDTO(rs.getString("name"), rs.getDouble("unit_price"), rs.getString("supermarket"),
                        rs.getString("image_url")));

            }
        }

        return results;
    }
}
