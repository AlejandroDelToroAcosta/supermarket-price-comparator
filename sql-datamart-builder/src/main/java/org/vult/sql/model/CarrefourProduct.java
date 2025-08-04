package org.vult.sql.model;


public class CarrefourProduct {
    private String name;
    private double price;
    private String measureUnit;
    private String url;
    private String productId;
    private String categoryID;
    private String category;
    private String marketName;
    private String imageURL;

    public CarrefourProduct(String name, double price, String measureUnit, String url, String productId, String categoryID, String category, String marketName, String imageURL) {
        this.name = name;
        this.price = price;
        this.measureUnit = measureUnit;
        this.url = url;
        this.productId = productId;
        this.categoryID = categoryID;
        this.category = category;
        this.marketName = marketName;
        this.imageURL = imageURL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }


    @Override
    public String toString() {
        return "CarrefourProduct{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", measureUnit='" + measureUnit + '\'' +
                ", url='" + url + '\'' +
                ", productId='" + productId + '\'' +
                ", categoryID='" + categoryID + '\'' +
                ", category='" + category + '\'' +
                ", marketName='" + marketName + '\'' +
                '}';
    }
}
