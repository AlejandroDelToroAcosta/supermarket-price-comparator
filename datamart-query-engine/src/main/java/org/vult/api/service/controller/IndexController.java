package org.vult.api.service.controller;

import spark.ModelAndView;

import java.util.HashMap;

public class IndexController {

    public static ModelAndView handleIndex(spark.Request req, spark.Response res) {
        return new ModelAndView(new HashMap<>(), "index.mustache");
    }
}
