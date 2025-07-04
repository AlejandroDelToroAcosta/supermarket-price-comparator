package org.vult.sql.model;

public class MercadonaProduct {
    private String productId;
    private String displayName;
    private double unitPrice;
    private String categoryName;
    private String categoryId;
    private String format;
    private String unitSize;
    private String slug;
    private String packaging;
    private String url;

    public MercadonaProduct(String productId, String displayName, double unitPrice, String categoryName, String categoryId, String format, String unitSize, String slug, String packaging, String url) {
        this.productId = productId;
        this.displayName = displayName;
        this.unitPrice = unitPrice;
        this.categoryName = categoryName;
        this.categoryId = categoryId;
        this.format = format;
        this.unitSize = unitSize;
        this.slug = slug;
        this.packaging = packaging;
        this.url = url;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getUnitSize() {
        return unitSize;
    }

    public void setUnitSize(String unitSize) {
        this.unitSize = unitSize;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getPackaging() {
        return packaging;
    }

    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "MercadonaProduct{" +
                "productId='" + productId + '\'' +
                ", displayName='" + displayName + '\'' +
                ", unitPrice=" + unitPrice +
                ", categoryName='" + categoryName + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", format='" + format + '\'' +
                ", unitSize='" + unitSize + '\'' +
                ", slug='" + slug + '\'' +
                ", packaging='" + packaging + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
