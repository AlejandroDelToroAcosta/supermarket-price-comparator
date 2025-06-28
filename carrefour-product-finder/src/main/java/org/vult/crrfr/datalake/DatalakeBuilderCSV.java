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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("DisplayName,UnitPrice,Unit,URL,ProductID,CategoryID");
            writer.newLine();
            for (Product p : productList) {
                writer.write(String.format(Locale.US, "%s,%.2f,%s,%s,%s,%s\n",
                        escapeCsv(p.getName()),
                        p.getPrice(),
                        escapeCsv(p.getMeasureUnit()),
                        escapeCsv(p.getUrl()),
                        escapeCsv(p.getProductId()),
                        escapeCsv(p.getCategory().getCategoryID())
                ));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private String escapeCsv(String value) {
        if (value == null) return "Na";
        return value.replace("\"", "\"\""); // duplica las comillas internas
    }

}
