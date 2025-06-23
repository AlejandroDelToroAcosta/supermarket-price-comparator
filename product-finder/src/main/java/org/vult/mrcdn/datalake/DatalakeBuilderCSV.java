package org.vult.mrcdn.datalake;

import org.vult.mrcdn.model.Product;

import java.io.*;

import java.util.List;
import java.util.Locale;


public class DatalakeBuilderCSV {
    public void write(List<Product> products, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Cabecera
            writer.write("ProductId,DisplayName,UnitPrice,CategoryName,CategoryId,Format,UnitSize,Slug,Packaging,URL");
            writer.newLine();

            for (Product p : products) {
                String line = String.format(Locale.US,
                        "\"%s\",\"%s\",%.2f,\"%s\",%d,\"%s\",%.2f,\"%s\",\"%s\",\"%s\"",
                        escape(p.getProductId()),
                        escape(p.getDisplayName()),
                        p.getUnitPrice(),
                        escape(p.getCategory().getName()),
                        p.getCategory().getId(),
                        escape(p.getFormat()),
                        p.getUnitSize(),
                        escape(p.getSlug()),
                        escape(p.getPackaging()),
                        escape(p.getUrl())
                );
                writer.write(line);
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String escape(String value) {
        if (value == null) return "Na";
        return value.replace("\"", "\"\""); // duplica las comillas internas
    }
}
