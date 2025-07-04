package org.vult.sql.service;

import org.vult.sql.model.CarrefourProduct;
import org.vult.sql.model.MercadonaProduct;
import org.vult.sql.schema.SQLSchema;

import java.sql.*;
import java.util.List;

import static javax.management.remote.JMXConnectorFactory.connect;

public class SQLiteProductRepository {

    private final String dbPath;

    public SQLiteProductRepository(String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:" + dbPath);
    }

    public void initializeSchema() throws SQLException {
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(SQLSchema.CREATE_CARREFOUR_TABLE);
            stmt.execute(SQLSchema.CREATE_MERCADONA_TABLE);
        }
    }
    public void insertMercadonaProducts(List<MercadonaProduct> products) throws SQLException {
        String sql = """
                    INSERT OR REPLACE INTO mercadona_products (
                        product_id, name, unit_price, category_name, category_id, 
                        format, unit_size, slug, packaging, url
                    ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (MercadonaProduct p : products) {
                pstmt.setString(1, p.getProductId());
                pstmt.setString(2, p.getDisplayName());
                pstmt.setDouble(3, p.getUnitPrice());
                pstmt.setString(4, p.getCategoryName());
                pstmt.setString(5, p.getCategoryId());
                pstmt.setString(6, p.getFormat());
                pstmt.setString(7, p.getUnitSize());
                pstmt.setString(8, p.getSlug());
                pstmt.setString(9, p.getPackaging());
                pstmt.setString(10, p.getUrl());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        }
    }

    public void insertCarrefourProducts(List<CarrefourProduct> products, String tableName) throws SQLException {
        String sql = String.format("""
            INSERT OR REPLACE INTO %s 
            (product_id, name, unit_price, unit, url, category_id, category)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """, tableName);

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (CarrefourProduct p : products) {
                pstmt.setString(1, p.getProductId());
                pstmt.setString(2, p.getName());
                pstmt.setDouble(3, p.getPrice());
                pstmt.setString(4, p.getMeasureUnit());
                pstmt.setString(5, p.getUrl());
                pstmt.setString(6, p.getCategoryID());
                pstmt.setString(7, p.getCategory());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        }
    }
}
