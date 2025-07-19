package org.vult.sql.schema;
public class SQLSchema {

    public static final String CREATE_CARREFOUR_TABLE = """
    CREATE TABLE IF NOT EXISTS carrefour_products (
        product_id TEXT PRIMARY KEY,
        name TEXT,
        unit_price REAL,
        unit TEXT,
        url TEXT,
        category_id TEXT,
        category TEXT,
        supermarket TEXT
    );
    """;

    public static final String CREATE_MERCADONA_TABLE = """
    CREATE TABLE IF NOT EXISTS mercadona_products (
        product_id TEXT PRIMARY KEY,
        name TEXT,
        unit_price REAL,
        category_name TEXT,
        category_id TEXT,
        format TEXT,
        unit_size TEXT,
        slug TEXT,
        packaging TEXT,
        url TEXT,
        supermarket TEXT
    );
    """;
}


