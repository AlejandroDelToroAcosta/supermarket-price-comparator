package org.vult.cmp.control;

import org.vult.cmp.model.Category;
import org.vult.cmp.model.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    public void execute(String url) throws IOException {
        List<Category> categories;
        List<Product> products;
        categories = service.fetchCategories(url);

        for (Category category: categories) {
            products = service.fetchProductsByCategory(category);

        }

    }
}
