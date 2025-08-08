package org.vult.api.service.controller;

import org.vult.sql.model.MercadonaProductDTO;
import org.vult.sql.query.CarrefourQueryService;
import org.vult.sql.query.MercadonaQueryService;
import org.vult.api.service.model.ProductMapper;
import spark.ModelAndView;

import java.util.*;

public class FilterController {
    private final MercadonaQueryService mercadonaService;
    private final CarrefourQueryService carrefourService;

    public FilterController(String dbPath) {
        this.mercadonaService = new MercadonaQueryService(dbPath);
        this.carrefourService = new CarrefourQueryService(dbPath);
    }

    public ModelAndView handle(spark.Request req, spark.Response res) {
        String market = req.queryParams("market");
        String keyword = req.queryParams("keyword");
        String category = req.queryParams("category");

        List<Map<String, Object>> productos = new ArrayList<>();

        try {
            if ("mercadona".equalsIgnoreCase(market)) {
                mercadonaService.searchMercadonaByNameAndCategory(keyword, category).forEach(p ->
                        productos.add(ProductMapper.mapMercadona((MercadonaProductDTO) p)));
            } else if ("carrefour".equalsIgnoreCase(market)) {
                carrefourService.searchCarrefourByNameAndCategory(keyword, category).forEach(p ->
                        productos.add(ProductMapper.mapCarrefour(p)));
            }
        } catch (Exception e) {
            res.status(500);
            return new ModelAndView(Map.of("error", e.getMessage()), "error.mustache");
        }

        Map<String, Object> model = new HashMap<>();
        model.put("productos", productos);
        model.put("category", category);
        model.put("market", market);
        model.put("keyword", keyword);

        return new ModelAndView(model, "filters.mustache");
    }
}
