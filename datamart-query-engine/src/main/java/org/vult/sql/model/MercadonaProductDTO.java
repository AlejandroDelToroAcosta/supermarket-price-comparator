package org.vult.sql.model;

import java.util.PrimitiveIterator;

public class MercadonaProductDTO extends ProductDTO{

    private double unitSize;

    public MercadonaProductDTO(String name, double price, String marketName, Double unitSize) {
        super(name, price, marketName);
        this.unitSize = unitSize;
    }

    public double getUnitSize() {
        return unitSize;
    }
}
