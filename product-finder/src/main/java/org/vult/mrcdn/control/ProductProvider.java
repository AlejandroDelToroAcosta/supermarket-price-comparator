package org.vult.mrcdn.control;

import org.vult.mrcdn.model.Category;
import org.vult.mrcdn.model.Product;

import java.io.IOException;
import java.util.List;

public interface ProductProvider {
    List<Category> getCategory(String url) throws IOException;
    List<Product> getProduct(String url) throws IOException;
}
