package org.vult.cmp.model;

public class Product {
    private float unitPrice;
    private String displayName;
    private Category category;

    public Product(float unitPrice, String displayName, Category category) {
        this.unitPrice = unitPrice;
        this.displayName = displayName;
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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
                ", unitPrice=" + unitPrice +
                ", displayName='" + displayName + '\'' +
                ", category=" + category +
                '}';
    }
}
