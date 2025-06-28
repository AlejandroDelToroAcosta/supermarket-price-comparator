package org.vult.crrfr.control;

import org.vult.crrfr.model.Category;
import org.vult.crrfr.model.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductService {

    private final ProductProvider provider;

    public ProductService(ProductProvider provider) {
        this.provider = provider;
    }

    public List<Category> fetchCategories(String url) throws IOException {
        return provider.getCategory(url);
    }

    public List<Product> fetchProductsByCategory(List<Category> categories) throws IOException {

        List<Category> filteredCats = categories.stream()
                .filter(cat -> cat.getUrl().startsWith("/supermercado/")
                        && !cat.getUrl().startsWith("/supermercado/ofertas"))
                .collect(Collectors.toList());

        List<Product> products = new ArrayList<>();

        for (Category category : filteredCats) {
            try {
                List<Product> productosCategoria = provider.getProduct(category);
                products.addAll(productosCategoria);

            } catch (IOException e) {
                System.err.println("Error en categoría: " + category.getUrl());
                e.printStackTrace();
            }
        }
        return products;
    }
}
