package org.vult.api.service.controller;

import java.util.*;

public class SaveController {
    private static final List<Map<String, Object>> productosGuardados = new ArrayList<>();

    public Object handle(spark.Request req, spark.Response res) {
        String name = req.queryParams("name");
        String price = req.queryParams("price");
        String marketName = req.queryParams("supermarket");
        String unitSize = req.queryParams("unitSize");

        Map<String, Object> producto = new HashMap<>();
        producto.put("name", name);
        producto.put("price", price);
        producto.put("supermarket", marketName);

        if (unitSize != null && !unitSize.isEmpty()) {
            producto.put("unitSize", unitSize);
        }

        productosGuardados.add(producto);

        res.redirect("/guardados");
        return null;
    }

    public static List<Map<String, Object>> getProductosGuardados() {
        return productosGuardados;
    }
}
