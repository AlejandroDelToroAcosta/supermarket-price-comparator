package org.vult.crrfr.model;

public class Product {
    private String name;
    private double price;
    private String measureUnit;
    private String url;
    private String productId;
    private Category category;
    private String imageURL;

    public Product(String name, double price, String measureUnit, String url, String productId, Category category, String imageURL) {
        this.name = name;
        this.price = price;
        this.measureUnit = measureUnit;
        this.url = url;
        this.productId = productId;
        this.category = category;
        this.imageURL = imageURL;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
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

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", measureUnit='" + measureUnit + '\'' +
                ", url='" + url + '\'' +
                ", productId='" + productId + '\'' +
                ", category=" + category +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }
}
