package org.vult.dia.model;

public class Product {
    private String displayName;
    private String id;
    private String imageUrl;
    private double price;
    private String unit;

    public Product(String displayName, String id, String imageUrl, double price, String unit) {
        this.displayName = displayName;
        this.id = id;
        this.imageUrl = imageUrl;
        this.price = price;
        this.unit = unit;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Product{" +
                "displayName='" + displayName + '\'' +
                ", id='" + id + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", price=" + price +
                ", unit='" + unit + '\'' +
                '}';
    }
}
