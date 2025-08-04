package org.vult.mrcdn.model;

public class Product {
    private double unitPrice;
    private String displayName;
    private Category category;
    private String productId;
    private String packaging;
    private String url;
    private double unitSize;
    private String format;
    private String slug;
    private String image;

    public Product(double unitPrice, String displayName, Category category, String productId, String packaging,
                   String url, double unitSize, String format, String slug, String image) {

        this.unitPrice = unitPrice;
        this.displayName = displayName;
        this.category = category;
        this.productId = productId;
        this.packaging = packaging;
        this.url = url;
        this.unitSize = unitSize;
        this.format = format;
        this.slug = slug;
        this.image = image;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    public double getUnitPrice() {
        return unitPrice;
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

    public double getUnitSize() {
        return unitSize;
    }

    public void setUnitSize(double unitSize) {
        this.unitSize = unitSize;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Product{" +
                "unitPrice=" + unitPrice +
                ", displayName='" + displayName + '\'' +
                ", category=" + category +
                ", productId='" + productId + '\'' +
                ", packaging='" + packaging + '\'' +
                ", url='" + url + '\'' +
                ", unitSize=" + unitSize +
                ", format='" + format + '\'' +
                ", slug='" + slug + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
