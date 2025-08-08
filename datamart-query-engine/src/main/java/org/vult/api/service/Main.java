package org.vult.api.service;


import static spark.Spark.exception;
import static spark.Spark.staticFiles;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Database path missing as argument");
        }

        String dbPath = args[0];
        staticFiles.location("/public");

        exception(Exception.class, (e, req, res) -> {
            e.printStackTrace();
            res.status(500);
            res.body("Error interno del servidor: " + e.getMessage());
        });

        new Router(dbPath).initRoutes();
    }
}
