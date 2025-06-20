package org.vult.cmp.control;

import org.vult.cmp.model.Category;
import org.vult.cmp.model.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface ProductProvider {
    List<Category> getCategory(String url) throws IOException;
    List<Product> getProduct(String url) throws IOException;
}
