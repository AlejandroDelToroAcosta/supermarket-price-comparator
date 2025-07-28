package org.vult.dia.model;

public class Category {
    private String path;
    private String parameter;

    public Category(String path, String parameter) {
        this.path = path;
        this.parameter = parameter;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    @Override
    public String toString() {
        return "Category{" +
                "path='" + path + '\'' +
                ", parameter='" + parameter + '\'' +
                '}';
    }
}
