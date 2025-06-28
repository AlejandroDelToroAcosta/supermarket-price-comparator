package org.vult.crrfr.model;

public class Category {
    private String url;
    private String categoryID;

    public Category(String url,String categoryID) {
        this.url = url;
        this.categoryID = categoryID;
    }

    public String getUrl() {
        return url;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Category{" +
                "url='" + url + '\'' +
                ", categoryID='" + categoryID + '\'' +
                '}';
    }
}
