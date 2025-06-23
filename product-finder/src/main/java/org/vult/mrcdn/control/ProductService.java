package org.vult.mrcdn.control;

import org.vult.mrcdn.model.Category;
import org.vult.mrcdn.model.Product;

import java.io.IOException;
import java.util.List;

public class ProductService {

    private final ProductProvider provider;

    public ProductService(ProductProvider provider) {
        this.provider = provider;
    }

    public List<Category> fetchCategories(String url) throws IOException {
        return provider.getCategory(url);
    }

    public List<Product> fetchProductsByCategory(Category category) throws IOException {
        List<Product> products;
        String url = "https://tienda.mercadona.es/api/categories/" + category.getId();
        products = provider.getProduct(url);
        return products;
    }
}
