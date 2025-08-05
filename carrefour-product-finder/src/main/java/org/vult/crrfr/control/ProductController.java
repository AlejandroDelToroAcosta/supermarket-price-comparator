package org.vult.crrfr.control;

import org.vult.crrfr.datalake.DatalakeBuilderCSV;
import org.vult.crrfr.model.Category;
import org.vult.crrfr.model.Product;

import java.io.IOException;

import java.util.List;


public class ProductController {

    private final ProductService service;
    private final String path;

    public ProductController(ProductService service, String path) {
        this.service = service;
        this.path = path;
    }

    public void execute(String categoryBaseUrl) throws IOException {
        List<Category> categories = service.fetchCategories(categoryBaseUrl);
        List<Product> products = service.fetchProductsByCategory(categories);

        DatalakeBuilderCSV datalakeBuilderCSV = new DatalakeBuilderCSV();

        datalakeBuilderCSV.write(products,path);
    }
}
