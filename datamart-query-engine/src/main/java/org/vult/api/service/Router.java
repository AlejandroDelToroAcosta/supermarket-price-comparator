package org.vult.api.service;
import org.vult.api.service.controller.*;
import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;
import static spark.Spark.*;

import java.util.*;
public class Router {
    private final String dbPath;

    public Router(String dbPath) {
        this.dbPath = dbPath;
    }

    public void initRoutes() {
        get("/", IndexController::handleIndex, new MustacheTemplateEngine());
        get("/filtrar", new FilterController(dbPath)::handle, new MustacheTemplateEngine());
        post("/guardar", new SaveController()::handle);
        get("/guardados", SavedProductsController::handle, new MustacheTemplateEngine());
        get("/buscar", new SearchController(dbPath)::handle, new MustacheTemplateEngine());
    }
}
