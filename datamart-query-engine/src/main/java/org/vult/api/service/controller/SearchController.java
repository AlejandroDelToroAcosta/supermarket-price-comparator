package org.vult.api.service.controller;
import org.vult.api.service.model.ProductMapper;
import org.vult.sql.model.MercadonaProductDTO;
import org.vult.sql.query.CarrefourQueryService;
import org.vult.sql.query.MercadonaQueryService;
import spark.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchController {
    private final MercadonaQueryService mercadonaService;
    private final CarrefourQueryService carrefourService;

    public SearchController(String dbPath) {
        this.mercadonaService = new MercadonaQueryService(dbPath);
        this.carrefourService = new CarrefourQueryService(dbPath);
    }

    public ModelAndView handle(spark.Request req, spark.Response res) {
        String keyword = req.queryParams("keyword");

        List<Map<String, Object>> mercadonaProductos = new ArrayList<>();
        List<Map<String, Object>> carrefourProductos = new ArrayList<>();
        List<Map<String, Object>> mercadonaCategoriasModel = new ArrayList<>();
        List<Map<String, Object>> carrefourCategoriasModel = new ArrayList<>();

        try {
            mercadonaService.searchMercadonaProduct(keyword).forEach(p ->
                    mercadonaProductos.add(ProductMapper.mapMercadona((MercadonaProductDTO) p)));

            mercadonaService.getCategoriesForNameSearch_Mercadona(keyword).forEach(cat ->
                    mercadonaCategoriasModel.add(Map.of(
                            "name", cat,
                            "url", "/filtrar?market=mercadona&keyword=" + keyword + "&category=" + cat
                    )));

            carrefourService.searchCarrefourByName(keyword).forEach(p ->
                    carrefourProductos.add(ProductMapper.mapCarrefour(p)));

            carrefourService.getCategoriesForNameSearch_Carrefour(keyword).forEach(cat ->
                    carrefourCategoriasModel.add(Map.of(
                            "name", cat,
                            "url", "/filtrar?market=carrefour&keyword=" + keyword + "&category=" + cat
                    )));

        } catch (Exception e) {
            res.status(500);
            return new ModelAndView(Map.of("error", e.getMessage()), "error.mustache");
        }

        Map<String, Object> model = new HashMap<>();
        model.put("keyword", keyword);
        model.put("supermercados", List.of(
                Map.of("nombre", "Mercadona", "id", "mercadona-row", "productos", mercadonaProductos, "categorias", mercadonaCategoriasModel),
                Map.of("nombre", "Carrefour", "id", "carrefour-row", "productos", carrefourProductos, "categorias", carrefourCategoriasModel)
        ));

        return new ModelAndView(model, "results.mustache");
    }
}
