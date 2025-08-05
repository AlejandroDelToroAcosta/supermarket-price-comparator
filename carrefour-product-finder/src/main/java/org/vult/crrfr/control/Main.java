package org.vult.crrfr.control;

import java.io.IOException;

public class Main {

    private static final String path = "https://www.carrefour.es/cloud-api/categories-api/v1/categories/menu/";

    public static void main(String[] args) throws IOException {
        String carrefourUrl = path;
        ProductController controller = new ProductController(
                new ProductService(new ProductMapProvider()), args[0]
        );
        controller.execute(carrefourUrl);

    }
}
