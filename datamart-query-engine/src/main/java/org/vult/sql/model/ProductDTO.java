package org.vult.sql.model;

public class ProductDTO {
    private final String name;
    private final double price;
    private final String marketName;
    private final String imageURL;

    public ProductDTO(String name, double price, String marketName, String imageURL) {
        this.name = name;
        this.price = price;
        this.marketName = marketName;
        this.imageURL = imageURL;
    }


    public String getMarketName() {
        return marketName;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
}
