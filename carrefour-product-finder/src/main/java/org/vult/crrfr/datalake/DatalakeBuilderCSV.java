package org.vult.crrfr.datalake;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.vult.crrfr.model.Product;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class DatalakeBuilderCSV {

    public void write(List<Product> productList, String filePath) {
        try (
                BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .withHeader("DisplayName", "UnitPrice", "Unit", "URL", "ProductID", "CategoryID", "ImageURL"))
        ) {
            for (Product p : productList) {
                csvPrinter.printRecord(
                        p.getName(),
                        String.format(Locale.US, "%.2f", p.getPrice()),
                        p.getMeasureUnit(),
                        p.getUrl(),
                        p.getProductId(),
                        p.getCategory().getCategoryID(),
                        p.getImageURL()
                );
            }
            csvPrinter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
