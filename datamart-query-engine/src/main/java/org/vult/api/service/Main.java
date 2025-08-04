package org.vult.api.service;
import static spark.Spark.*;

import org.vult.sql.model.MercadonaProductDTO;
import org.vult.sql.query.CarrefourQueryService;
import org.vult.sql.query.MercadonaQueryService;
import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.*;

public class Main {
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
                        item.put("supermarket", p.getMarketName());
                        if (p instanceof MercadonaProductDTO mercadonaProd) {
                            item.put("unitSize", mercadonaProd.getUnitSize());
                        }
                        productos.add(item);
                    }
                } else {
                    var results = new CarrefourQueryService("C:\\Users\\aadel\\Desktop\\GCID\\Tercero\\Segundo Cuatrimestre\\BDNR\\fitness-db\\market-comparator\\database.db").searchCarrefourByNameAndCategory(keyword, category);
                    for (var p : results) {
                        Map<String, Object> item = new HashMap<>();
                        item.put("name", p.getName());
                        item.put("price", p.getPrice());
                        item.put("supermarket", p.getMarketName());
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
            String marketName = req.queryParams("supermarket");
            String unitSize = req.queryParams("unitSize"); // puede ser null

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
        });

        get("/guardados", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("productos", productosGuardados);
            return new ModelAndView(model, "guardados.mustache");
        }, new MustacheTemplateEngine());

        get("/buscar", (req, res) -> {
            String keyword = req.queryParams("keyword");

            List<Map<String, Object>> mercadonaProductos = new ArrayList<>();
            List<Map<String, Object>> carrefourProductos = new ArrayList<>();
            List<Map<String, Object>> mercadonaCategoriasModel = new ArrayList<>();
            List<Map<String, Object>> carrefourCategoriasModel = new ArrayList<>();

            try {
                var mercadonaService = new MercadonaQueryService("C:\\Users\\aadel\\Desktop\\GCID\\Tercero\\Segundo Cuatrimestre\\BDNR\\fitness-db\\market-comparator\\database.db");
                var carrefourService = new CarrefourQueryService("C:\\Users\\aadel\\Desktop\\GCID\\Tercero\\Segundo Cuatrimestre\\BDNR\\fitness-db\\market-comparator\\database.db");

                // ✅ Mercadona
                var mercadonaResults = mercadonaService.searchMercadonaProduct(keyword);
                for (var p : mercadonaResults) {
                    Map<String, Object> item = new HashMap<>();
                    item.put("name", p.getName());
                    item.put("price", p.getPrice());
                    item.put("supermarket", p.getMarketName());
                    item.put("imageUrl", p.getImageURL());

                    if (p instanceof MercadonaProductDTO mercadonaProd) {
                        item.put("unitSize", mercadonaProd.getUnitSize());
                    }
                    mercadonaProductos.add(item);
                }

                var mercadonaCategorias = mercadonaService.getCategoriesForNameSearch_Mercadona(keyword);
                for (String cat : mercadonaCategorias) {
                    Map<String, Object> catMap = new HashMap<>();
                    catMap.put("name", cat);
                    catMap.put("url", "/filtrar?market=mercadona&keyword=" + keyword + "&category=" + cat);
                    mercadonaCategoriasModel.add(catMap);
                }

                // ✅ Carrefour
                var carrefourResults = carrefourService.searchCarrefourByName(keyword);
                for (var p : carrefourResults) {
                    Map<String, Object> item = new HashMap<>();
                    item.put("name", p.getName());
                    item.put("price", p.getPrice());
                    item.put("supermarket", p.getMarketName());
                    item.put("imageUrl", p.getImageURL());
                    carrefourProductos.add(item);
                }

                var carrefourCategorias = carrefourService.getCategoriesForNameSearch_Carrefour(keyword);
                for (String cat : carrefourCategorias) {
                    Map<String, Object> catMap = new HashMap<>();
                    catMap.put("name", cat);
                    catMap.put("url", "/filtrar?market=carrefour&keyword=" + keyword + "&category=" + cat);
                    carrefourCategoriasModel.add(catMap);
                }

            } catch (Exception e) {
                e.printStackTrace();
                res.status(500);
                return new ModelAndView(Map.of("error", e.getMessage()), "error.mustache");
            }

            Map<String, Object> model = new HashMap<>();
            model.put("keyword", keyword);

            List<Map<String, Object>> supermercados = new ArrayList<>();
            supermercados.add(Map.of(
                    "nombre", "Mercadona",
                    "id", "mercadona-row",
                    "productos", mercadonaProductos,
                    "categorias", mercadonaCategoriasModel
            ));
            supermercados.add(Map.of(
                    "nombre", "Carrefour",
                    "id", "carrefour-row",
                    "productos", carrefourProductos,
                    "categorias", carrefourCategoriasModel
            ));

            model.put("supermercados", supermercados);

            return new ModelAndView(model, "results.mustache");
        }, new MustacheTemplateEngine());



    }
}