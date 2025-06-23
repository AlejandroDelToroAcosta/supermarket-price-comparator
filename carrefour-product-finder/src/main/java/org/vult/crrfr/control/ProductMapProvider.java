package org.vult.crrfr.control;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.vult.crrfr.model.Category;
import org.vult.crrfr.model.Product;
import org.vult.crrfr.utils.HttpClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductMapProvider {

    public static List<Category> getCategory(String url) throws IOException {
        String json = HttpClient.get(url);
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        JsonArray menu = jsonObject.get("menu").getAsJsonArray();
        List<Category> categories = new ArrayList<>();
        for (int i = 0; i<menu.size();i++) {
            JsonObject menuObj = menu.get(i).getAsJsonObject();
            JsonArray childs = menuObj.get("childs").getAsJsonArray();
            if (childs != null ) {
                for (int j=0; j<childs.size();j++){
                    JsonObject child = childs.get(j).getAsJsonObject();
                    String urlRel = child.get("url_rel").getAsString();
                    Category category = new Category(urlRel);
                    categories.add(category);
                }
            }
        }
        return categories;
    }
    public static List<Product> getProductByCategory(String categoryUrl) throws IOException {
        List<Product> products = new ArrayList<>();
        String productUrl = "https://www.carrefour.es/cloud-api/plp-food-papi/v1" + categoryUrl;

        String json = HttpClient.get(productUrl);
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);

        JsonObject results = jsonObject.get("results").getAsJsonObject();


        JsonArray resultsArray = results.get("items").getAsJsonArray();

        for (int j = 0; j<resultsArray.size();j++) {
            JsonObject arrayElement = resultsArray.get(j).getAsJsonObject();
            String name = arrayElement.get("name").getAsString();

            String stringPrice = arrayElement.get("price").getAsString();
            String newPrice = stringPrice.replace("€", "").trim();
            String newPriceWithoutComa = newPrice.replace("," , ".");
            float finalPrice = Float.parseFloat(newPriceWithoutComa);

            String productId = arrayElement.get("product_id").getAsString();
            String url = arrayElement.get("url").getAsString();
            String measureUnit = arrayElement.get("measure_unit").getAsString();

            Product product = new Product(name,finalPrice,measureUnit,url,productId);
            products.add(product);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new IOException("Proceso interrumpido durante la espera", e);
            }
        }



        return products;
    }

    public static void main(String[] args) throws IOException {
        String categoryBaseUrl = "https://www.carrefour.es/cloud-api/categories-api/v1/categories/menu/";
        List<Category> categories = getCategory(categoryBaseUrl);
        List<Category> filteredCats = categories.stream()
                .filter(cat -> cat.getUrl().startsWith("/supermercado/")
                && !cat.getUrl().startsWith("/supermercado/ofertas"))
                .collect(Collectors.toList());
        System.out.println("categorias: " + categories.size());

        List<Product> products = new ArrayList<>();

        for (Category category : filteredCats) {
            try {
                List<Product> productosCategoria = getProductByCategory(category.getUrl());
                products.addAll(productosCategoria);
                System.out.println("Prodcutos: " + products.size());
            } catch (IOException e) {
                System.err.println("Error en categoría: " + category.getUrl());
                e.printStackTrace();
            }
        }
    }
}
