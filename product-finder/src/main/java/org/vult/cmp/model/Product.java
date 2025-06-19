package org.vult.cmp.model;

public class Product {
    private String categories;
    private float unitPrice;
    private String displayName;

    public Product(String categories, float unitPrice, String displayName) {
        this.categories = categories;
        this.unitPrice = unitPrice;
        this.displayName = displayName;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return "Product{" +
                "categories='" + categories + '\'' +
                ", unitPrice=" + unitPrice +
                ", displayName='" + displayName + '\'' +
                '}';
    }
}
