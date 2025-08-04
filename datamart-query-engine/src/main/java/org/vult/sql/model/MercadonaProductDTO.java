package org.vult.sql.model;

import java.util.PrimitiveIterator;

public class MercadonaProductDTO extends ProductDTO{

    private double unitSize;

    public MercadonaProductDTO(String name, double price, String marketName, String imageURL, Double unitSize) {
        super(name, price, marketName, imageURL);
        this.unitSize = unitSize;
    }

    public double getUnitSize() {
        return unitSize;
    }
}
