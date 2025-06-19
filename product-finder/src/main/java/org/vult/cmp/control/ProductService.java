package org.vult.cmp.control;

import org.vult.cmp.model.Category;
import org.vult.cmp.model.Product;

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

    public List<Product> fetchProductsByCategory(Category category) {
        String url = "https://tienda.mercadona.es/api/categories/" + category.getId(); // ejemplo
        return provider.getProduct(url);
    }
}
