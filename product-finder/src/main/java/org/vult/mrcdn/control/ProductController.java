package org.vult.mrcdn.control;

import org.vult.mrcdn.model.Category;
import org.vult.mrcdn.model.Product;
import org.vult.mrcdn.datalake.DatalakeBuilderCSV;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductController {

    private final ProductService service;
    private final String outputPath;

    public ProductController(ProductService service, String outputPath) {
        this.service = service;
        this.outputPath = outputPath;
    }

    public void execute(String url) throws IOException {
        List<Category> categories = service.fetchCategories(url);
        List<Product> allProducts = new ArrayList<>();

        for (Category category : categories) {
            List<Product> productsByCategory = service.fetchProductsByCategory(category);
            allProducts.addAll(productsByCategory);
            System.out.println(productsByCategory);
        }

        DatalakeBuilderCSV datalakeBuilderCSV = new DatalakeBuilderCSV();
        datalakeBuilderCSV.write(allProducts, outputPath);


    }
}
