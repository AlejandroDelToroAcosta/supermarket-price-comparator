package org.vult.sql.model;

public class ProductDTO {
    private final String name;
    private final double price;
    private final String marketName;

    public ProductDTO(String name, double price, String marketName) {
        this.name = name;
        this.price = price;
        this.marketName = marketName;
    }


    public String getMarketName() {
        return marketName;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
}
