package org.vult.crrfr.model;

public class Category {
    private String url;

    public Category(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Category{" +
                "url='" + url + '\'' +
                '}';
    }
}
