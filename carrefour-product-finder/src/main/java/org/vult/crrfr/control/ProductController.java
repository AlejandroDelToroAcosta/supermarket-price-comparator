package org.vult.crrfr.control;

import org.vult.crrfr.datalake.DatalakeBuilderCSV;
import org.vult.crrfr.model.Category;
import org.vult.crrfr.model.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    public void execute(String categoryBaseUrl) throws IOException {
        List<Category> categories = service.fetchCategories(categoryBaseUrl);
        List<Product> products = service.fetchProductsByCategory(categories);


        DatalakeBuilderCSV datalakeBuilderCSV = new DatalakeBuilderCSV();

        datalakeBuilderCSV.write(products,"C:\\Users\\aadel\\Desktop\\GCID\\Tercero\\Segundo Cuatrimestre\\BDNR\\fitness-db\\market-comparator\\datalake\\carrefour-data.csv");
        System.out.println("Productos escritos");
    }
}
