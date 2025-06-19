package org.vult.cmp.control;

import org.vult.cmp.model.Category;

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
        categories = service.fetchCategories(url);

    }
}
