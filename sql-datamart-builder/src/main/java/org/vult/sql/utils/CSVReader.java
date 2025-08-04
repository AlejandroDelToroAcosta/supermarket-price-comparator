package org.vult.sql.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.vult.sql.model.CarrefourProduct;
import org.vult.sql.model.MercadonaProduct;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    public List<MercadonaProduct> readMercadonaCSV(String path) throws IOException {
        List<MercadonaProduct> products = new ArrayList<>();
        try (Reader reader = new FileReader(path)) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .parse(reader);

            for (CSVRecord record : records) {
                MercadonaProduct p = new MercadonaProduct(
                        record.get("ProductId"),
                        record.get("DisplayName"),
                        Double.parseDouble(record.get("UnitPrice")),
                        record.get("CategoryName"),
                        record.get("CategoryId"),
                        record.get("Format"),
                        record.get("UnitSize"),
                        record.get("Slug"),
                        record.get("Packaging"),
                        record.get("URL"),
                        record.get("ImageURL"),
                        record.get("Supermarket")
                );
                products.add(p);
            }
        }
        return products;
    }
    public List<CarrefourProduct> readCarrefourCSV(String filePath) throws IOException {
        List<CarrefourProduct> products = new ArrayList<>();
        try (Reader reader = new FileReader(filePath)) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .parse(reader);
            for (CSVRecord record: records ) {
                CarrefourProduct p = new CarrefourProduct(
                        record.get("DisplayName"),
                        Double.parseDouble(record.get("UnitPrice")),
                        record.get("Unit"),
                        record.get("URL"),
                        record.get("ProductID"),
                        record.get("CategoryID"),
                        record.get("Category"),
                        record.get("Supermarket"),
                        record.get("ImageURL")
                );
                products.add(p);
            }
        }
        return products;
    }
}
