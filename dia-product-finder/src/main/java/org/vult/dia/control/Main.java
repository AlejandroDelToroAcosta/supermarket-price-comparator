package org.vult.dia.control;

import org.vult.dia.control.ProductMapProvider;
import org.vult.dia.model.Category;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        ProductMapProvider productMapProvider = new ProductMapProvider();
        List<Category> categories = productMapProvider.getCategory("https://www.dia.es/api/v1/plp-insight/initial_analytics/charcuteria-y-quesos/jamon-cocido-lacon-fiambres-y-mortadela/c/L2001?navigation=L2001");

        for (Category category: categories) {
            productMapProvider.getProduct(category);
        }

    }
}
