package org.vult.crrfr.control;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String carrefourUrl = "https://www.carrefour.es/cloud-api/categories-api/v1/categories/menu/";
        ProductController controller = new ProductController(
                new ProductService(new ProductMapProvider())
        );
        controller.execute(carrefourUrl);

    }
}
