package org.vult.crrfr.control;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.vult.crrfr.utils.JsonConstants;
import org.vult.crrfr.model.Category;
import org.vult.crrfr.model.Product;
import org.vult.crrfr.utils.HttpClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductMapProvider implements ProductProvider {

    public List<Category> getCategory(String url) throws IOException {
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
                    String urlRel = child.get(JsonConstants.CATEGORY_URL).getAsString();
                    String categoryID = child.get(JsonConstants.CATEGORY_ID).getAsString();
                    Category category = new Category(urlRel,categoryID);
                    categories.add(category);
                }
            }
        }
        return categories;
    }
    public List<Product> getProduct(Category category) throws IOException {
        List<Product> products = new ArrayList<>();
        int offset = 0;
        boolean moreProducts = true;

        while (moreProducts && offset < 1008) {
            String productUrl = "https://www.carrefour.es/cloud-api/plp-food-papi/v1" + category.getUrl() +
                    "?offset=" + offset;

            String json = HttpClient.get(productUrl);
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(json, JsonObject.class);

            JsonObject results = jsonObject.getAsJsonObject("results");
            if (results == null || !results.has("items")) {
                System.out.println("Categoría vacía o malformada: " + category.getUrl());
                break;
            }            JsonArray itemsArray = results.getAsJsonArray("items");



            if (itemsArray.size() == 0) {
                moreProducts = false;
                break;
            }

            for (JsonElement element : itemsArray) {
                JsonObject obj = element.getAsJsonObject();

                String name = obj.has(JsonConstants.PRODUCT_NAME) ? obj.get(JsonConstants.PRODUCT_NAME).getAsString() : "N/A";
                String stringPrice = obj.has(JsonConstants.UNIT_PRICE) ? obj.get(JsonConstants.UNIT_PRICE).getAsString() : "0";
                String newPrice = stringPrice.replace("€", "").trim();
                String priceWithoutComa = newPrice.replace(",", ".");
                float finalPrice = Float.parseFloat(priceWithoutComa);

                JsonObject images = obj.get("images").getAsJsonObject();
                String imageURL = images.get("mobile").getAsString();

                String productId = obj.has(JsonConstants.PRODUCT_ID) ? obj.get(JsonConstants.PRODUCT_ID).getAsString() : "N/A";
                String url = obj.has(JsonConstants.URL) ? obj.get(JsonConstants.URL).getAsString() : "";
                String measureUnit = obj.has(JsonConstants.MEASURE_UNIT) ? obj.get(JsonConstants.MEASURE_UNIT).getAsString() : "";

                Product product = new Product(name, finalPrice, measureUnit, url, productId,category,imageURL);
                products.add(product);

            }

            offset += 24;


            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new IOException("Proceso interrumpido durante la espera", e);
            }

        }

        return products;
    }


}
