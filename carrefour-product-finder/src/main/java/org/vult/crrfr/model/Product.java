package org.vult.crrfr.model;

import kotlin.text.UStringsKt;

public class Product {
    private String name;
    private float price;
    private String measureUnit;
    private String url;
    private String productId;

    public Product(String name, float price, String measureUnit, String url, String productId) {
        this.name = name;
        this.price = price;
        this.measureUnit = measureUnit;
        this.url = url;
        this.productId = productId;
    }

    public void setPrice(float price) {
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
                '}';
    }
}
