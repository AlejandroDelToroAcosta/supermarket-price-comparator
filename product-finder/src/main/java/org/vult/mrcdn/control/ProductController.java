package org.vult.mrcdn.control;

import org.vult.mrcdn.model.Category;
import org.vult.mrcdn.model.Product;
import org.vult.mrcdn.datalake.DatalakeBuilderCSV;
import org.vult.mrcdn.utils.Serialize;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    public void execute(String url) throws IOException {
        List<Category> categories = service.fetchCategories(url);
        List<Product> allProducts = new ArrayList<>();  // esta lista acumula todos los productos

        for (Category category : categories) {
            List<Product> productsByCategory = service.fetchProductsByCategory(category);
            allProducts.addAll(productsByCategory);  // acumulamos
            System.out.println(productsByCategory);
        }

        DatalakeBuilderCSV datalakeBuilderCSV = new DatalakeBuilderCSV();
        String outputPath = "C:\\Users\\aadel\\Desktop\\GCID\\Tercero\\Segundo Cuatrimestre\\BDNR\\fitness-db\\market-comparator\\datalake\\mercadona-data.csv";
        datalakeBuilderCSV.write(allProducts, outputPath);


        Serialize serialize = new Serialize();
        for (Product product : allProducts) {
            Double size = product.getUnitSize();
            if (size == null || size.isNaN() || size.isInfinite()) {
                product.setUnitSize(0.0);
            }
        }
        serialize.serializeProducts(allProducts, "C:\\Users\\aadel\\Desktop\\GCID\\Tercero\\Segundo Cuatrimestre\\BDNR\\fitness-db\\market-comparator\\mercadona-data.json");

    }
}
