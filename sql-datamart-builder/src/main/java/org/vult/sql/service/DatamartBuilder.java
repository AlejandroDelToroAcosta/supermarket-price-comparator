package org.vult.sql.service;

import org.vult.sql.model.CarrefourProduct;
import org.vult.sql.model.MercadonaProduct;
import org.vult.sql.utils.CSVReader;

import java.util.List;

public class DatamartBuilder {
    private final String dbPath;
    private final String carrefourCSVPath;
    private final String mercadonaCSVPath;

    public DatamartBuilder(String dbPath, String carrefourCSVPath, String mercadonaCSVPath) {
        this.dbPath = dbPath;
        this.carrefourCSVPath = carrefourCSVPath;
        this.mercadonaCSVPath = mercadonaCSVPath;
    }

    public void execute() {

        try {
            SQLiteProductRepository repo = new SQLiteProductRepository(dbPath);
            repo.initializeSchema();

            CSVReader reader = new CSVReader();
            List<CarrefourProduct> carrefourProducts = reader.readCarrefourCSV(carrefourCSVPath);
            List<MercadonaProduct> mercadonaProducts = reader.readMercadonaCSV(mercadonaCSVPath);

            repo.insertCarrefourProducts(carrefourProducts, "carrefour_products");
            repo.insertMercadonaProducts(mercadonaProducts);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
