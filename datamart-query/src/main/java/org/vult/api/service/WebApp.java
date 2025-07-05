package org.vult.api.service;


import static spark.Spark.*;

import org.vult.sql.query.MercadonaQueryService;
import org.vult.sql.query.CarrefourQueryService;

import java.util.*;

public class WebApp {

    public static void main(String[] args) {
        port(4567);

        String dbPath = "C:\\Users\\aadel\\Desktop\\GCID\\Tercero\\Segundo Cuatrimestre\\BDNR\\fitness-db\\market-comparator\\database.db";
        MercadonaQueryService mercadonaService = new MercadonaQueryService(dbPath);
        CarrefourQueryService carrefourService = new CarrefourQueryService(dbPath);

        // Página de inicio con formulario
        get("/", (req, res) -> {
            return """
                <html>
                <body>
                    <h2>Buscador de productos</h2>
                    <form action="/buscar" method="get">
                        <label>Supermercado:</label>
                        <select name="market">
                            <option value="mercadona">Mercadona</option>
                            <option value="carrefour">Carrefour</option>
                        </select><br><br>
                        <label>Buscar producto:</label>
                        <input type="text" name="keyword" required><br><br>
                        <input type="submit" value="Buscar">
                    </form>
                </body>
                </html>
            """;
        });

        // Resultado de búsqueda
        get("/buscar", (req, res) -> {
            try {
                String market = req.queryParams("market");
                String keyword = req.queryParams("keyword");

                List<String> results = new ArrayList<>();
                Set<String> categories = new HashSet<>();

                if ("mercadona".equals(market)) {
                    results = mercadonaService.searchMercadonaByName(keyword);
                    categories = mercadonaService.getCategoriesForNameSearch_Mercadona(keyword);
                } else if ("carrefour".equals(market)) {
                    results = carrefourService.searchCarrefourByName(keyword);
                    categories = carrefourService.getCategoriesForNameSearch_Carrefour(keyword);
                }

                StringBuilder html = new StringBuilder("<html><body>");
                html.append("<h2>Resultados para '").append(keyword).append("'</h2>");

                if (results.isEmpty()) {
                    html.append("<p>No se encontraron productos.</p>");
                } else {
                    html.append("<ul>");
                    for (String r : results) {
                        html.append("<li>").append(r).append("</li>");
                    }
                    html.append("</ul>");
                }

                if (!categories.isEmpty()) {
                    html.append("<h3>Filtrar por categoría:</h3><ul>");
                    for (String c : categories) {
                        html.append("<li><a href='/filtrar?market=")
                                .append(market).append("&keyword=")
                                .append(keyword).append("&category=")
                                .append(c).append("'>")
                                .append(c).append("</a></li>");
                    }
                    html.append("</ul>");
                }

                html.append("<br><a href='/'>Volver</a>");
                html.append("</body></html>");
                return html.toString();

            } catch (Exception e) {
                e.printStackTrace(); // Imprime el error completo
                return "<h1>Ocurrió un error interno: " + e.getMessage() + "</h1>";
            }
        });
        // Filtro por categoría
        get("/filtrar", (req, res) -> {
            String market = req.queryParams("market");
            String keyword = req.queryParams("keyword");
            String category = req.queryParams("category");

            List<String> results;
            if ("mercadona".equals(market)) {
                results = mercadonaService.searchMercadonaByNameAndCategory(keyword, category);
            } else {
                results = carrefourService.searchCarrefourByNameAndCategory(keyword, category);
            }

            StringBuilder html = new StringBuilder("<html><body>");
            html.append("<h2>Resultados filtrados en '").append(category).append("'</h2>");
            html.append("<ul>");
            for (String r : results) {
                html.append("<li>").append(r).append("</li>");
            }
            html.append("</ul>");
            html.append("<br><a href='/'>Volver</a>");
            html.append("</body></html>");

            return html.toString();
        });
    }
}
