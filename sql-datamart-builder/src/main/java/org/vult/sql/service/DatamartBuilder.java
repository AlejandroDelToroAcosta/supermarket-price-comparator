package org.vult.sql.service;

import org.vult.sql.model.CarrefourProduct;
import org.vult.sql.model.MercadonaProduct;
import org.vult.sql.utils.CSVReader;

import java.util.List;

public class DatamartBuilder {
    public void execute() {
        String dbPath = "C:\\Users\\aadel\\Desktop\\GCID\\Tercero\\Segundo Cuatrimestre\\BDNR\\fitness-db\\market-comparator\\database.db";
        String carrefourCSV = "C:\\Users\\aadel\\Desktop\\market-comparator\\carrefour_data_new_column.csv";

        String mercadonaCSV = "C:\\Users\\aadel\\Desktop\\market-comparator\\new-mercadona-data.csv";
        try {
            SQLiteProductRepository repo = new SQLiteProductRepository(dbPath);
            repo.initializeSchema();

            CSVReader reader = new CSVReader();
            List<CarrefourProduct> carrefourProducts = reader.readCarrefourCSV(carrefourCSV);
            //List<MercadonaProduct> mercadonaProducts = reader.readMercadonaCSV(mercadonaCSV);

            repo.insertCarrefourProducts(carrefourProducts, "carrefour_products");
            //repo.insertMercadonaProducts(mercadonaProducts);

            System.out.println("Datamart creado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
