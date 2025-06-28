package org.vult.crrfr.control;

import org.vult.crrfr.model.Category;
import org.vult.crrfr.model.Product;

import java.io.IOException;
import java.util.List;

public interface ProductProvider {
    List<Category> getCategory(String url) throws IOException;
    List<Product> getProduct(Category category) throws IOException;
}
