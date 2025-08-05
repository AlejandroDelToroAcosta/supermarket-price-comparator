package org.vult.mrcdn.control;

import java.io.IOException;
public class Main {
    public static void main(String[] args) throws IOException {
        String mercadonaUrl = "https://tienda.mercadona.es/api/categories/";
        ProductController controller = new ProductController(
                new ProductService(new ProductMapProvider()), args[0]
        );
        controller.execute(mercadonaUrl);

    }
}
