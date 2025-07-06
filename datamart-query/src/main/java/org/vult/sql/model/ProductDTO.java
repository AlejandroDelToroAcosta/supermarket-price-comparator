package org.vult.sql.model;

public class ProductDTO {
    private final String name;
    private final double price;

    public ProductDTO(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
}
