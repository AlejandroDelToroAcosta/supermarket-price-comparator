package org.vult.api.service;
import static spark.Spark.*;

import org.vult.sql.query.CarrefourQueryService;
import org.vult.sql.query.MercadonaQueryService;
import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.*;

public class Main {
    // A nivel global, para guardar productos seleccionados temporalmente
    private static final List<Map<String, Object>> productosGuardados = new ArrayList<>();

    public static void main(String[] args) {

        staticFiles.location("/public");
        exception(Exception.class, (e, req, res) -> {
            e.printStackTrace(); // ✅ Verás el error real en consola
            res.status(500);
            res.body("Error interno del servidor: " + e.getMessage());
        });



        // Ruta principal con el formulario
        get("/", (req, res) -> {
            return new ModelAndView(new HashMap<>(), "index.mustache");
        }, new MustacheTemplateEngine());

        get("/filtrar", (req, res) -> {
            String market = req.queryParams("market");
            String keyword = req.queryParams("keyword");
            String category = req.queryParams("category");

            List<Map<String, Object>> productos = new ArrayList<>();

            try {
                if ("mercadona".equalsIgnoreCase(market)) {
                    var results = new MercadonaQueryService("C:\\Users\\aadel\\Desktop\\GCID\\Tercero\\Segundo Cuatrimestre\\BDNR\\fitness-db\\market-comparator\\database.db").searchMercadonaByNameAndCategory(keyword, category);
                    for (var p : results) {
                        Map<String, Object> item = new HashMap<>();
                        item.put("name", p.getName());
                        item.put("price", p.getPrice());
                        productos.add(item);
                    }
                } else {
                    var results = new CarrefourQueryService("C:\\Users\\aadel\\Desktop\\GCID\\Tercero\\Segundo Cuatrimestre\\BDNR\\fitness-db\\market-comparator\\database.db").searchCarrefourByNameAndCategory(keyword, category);
                    for (var p : results) {
                        Map<String, Object> item = new HashMap<>();
                        item.put("name", p.getName());
                        item.put("price", p.getPrice());
                        productos.add(item);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                res.status(500);
                return new ModelAndView(Map.of("error", e.getMessage()), "error.mustache");
            }

            Map<String, Object> model = new HashMap<>();
            model.put("productos", productos);
            model.put("category", category);
            model.put("market", market);
            model.put("keyword", keyword);

            return new ModelAndView(model, "filters.mustache");
        }, new MustacheTemplateEngine());

        post("/guardar", (req, res) -> {
            String name = req.queryParams("name");
            String price = req.queryParams("price");

            Map<String, Object> producto = new HashMap<>();
            producto.put("name", name);
            producto.put("price", price);

            productosGuardados.add(producto);

            // Redirigir de vuelta a la búsqueda o a una página de comparación
            res.redirect("/guardados");
            return null;
        });

        get("/guardados", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("productos", productosGuardados);
            return new ModelAndView(model, "guardados.mustache"); // o usa HTML directamente si no usas mustache
        }, new MustacheTemplateEngine());

        get("/buscar", (req, res) -> {
            String keyword = req.queryParams("keyword");
            String market = req.queryParams("market");

            List<Map<String, Object>> productos = new ArrayList<>();
            Set<String> categorias = new HashSet<>();

            try {
                if ("mercadona".equalsIgnoreCase(market)) {
                    var results = new MercadonaQueryService("C:\\Users\\aadel\\Desktop\\GCID\\Tercero\\Segundo Cuatrimestre\\BDNR\\fitness-db\\market-comparator\\database.db").searchMercadonaProduct(keyword);
                    for (var p : results) {
                        Map<String, Object> item = new HashMap<>();
                        item.put("name", p.getName());
                        item.put("price", p.getPrice());
                        productos.add(item);
                    }
                    categorias = new MercadonaQueryService("C:\\Users\\aadel\\Desktop\\GCID\\Tercero\\Segundo Cuatrimestre\\BDNR\\fitness-db\\market-comparator\\database.db").getCategoriesForNameSearch_Mercadona(keyword);
                } else if ("carrefour".equalsIgnoreCase(market)) {
                    var results = new CarrefourQueryService("C:\\Users\\aadel\\Desktop\\GCID\\Tercero\\Segundo Cuatrimestre\\BDNR\\fitness-db\\market-comparator\\database.db").searchCarrefourByName(keyword);
                    for (var p : results) {
                        Map<String, Object> item = new HashMap<>();
                        item.put("name", p.getName());
                        item.put("price", p.getPrice());
                        productos.add(item);
                    }
                    categorias = new CarrefourQueryService("C:\\Users\\aadel\\Desktop\\GCID\\Tercero\\Segundo Cuatrimestre\\BDNR\\fitness-db\\market-comparator\\database.db").getCategoriesForNameSearch_Carrefour(keyword);
                }
            } catch (Exception e) {
                e.printStackTrace();
                res.status(500);
                return new ModelAndView(Map.of("error", e.getMessage()), "error.mustache");
            }

            Map<String, Object> model = new HashMap<>();
            model.put("keyword", keyword);
            model.put("market", market);
            model.put("productos", productos);

            // Convertimos las categorías a una lista de objetos para Mustache
            List<Map<String, Object>> categoriasModel = new ArrayList<>();
            for (String cat : categorias) {
                Map<String, Object> catMap = new HashMap<>();
                catMap.put("name", cat);
                catMap.put("url", "/filtrar?market=" + market + "&keyword=" + keyword + "&category=" + cat);
                categoriasModel.add(catMap);
            }

            model.put("categorias", categoriasModel);

            return new ModelAndView(model, "results.mustache");
        }, new MustacheTemplateEngine());


    }
}