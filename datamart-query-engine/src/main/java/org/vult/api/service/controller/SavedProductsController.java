package org.vult.api.service.controller;

import spark.ModelAndView;

import java.util.*;

public class SavedProductsController {

    public static ModelAndView handle(spark.Request req, spark.Response res) {
        Map<String, Object> model = new HashMap<>();
        model.put("productos", SaveController.getProductosGuardados());
        return new ModelAndView(model, "guardados.mustache");
    }
}
