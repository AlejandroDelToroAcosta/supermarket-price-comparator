package org.vult.mrcdn.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.Gson;

import static org.vult.mrcdn.utils.JsonUtils.*;
import org.vult.mrcdn.utils.HttpClient;
import org.vult.mrcdn.utils.JsonConstants;
import org.vult.mrcdn.model.Category;
import org.vult.mrcdn.model.Product;

public class ProductMapProvider implements ProductProvider{


    public List<Category> getCategory(String url) throws IOException {

        ArrayList<Category> categoryArrayList = new ArrayList<>();
        String json = HttpClient.get(url);
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);

        JsonArray results = jsonObject.getAsJsonArray("results");


        for (int i = 0; i<results.size(); i++) {
            JsonObject obj = results.get(i).getAsJsonObject();

            JsonArray categories = obj.getAsJsonArray("categories");

            for (int j = 0; j< categories.size(); j++) {
                JsonObject categoryObj = categories.get(j).getAsJsonObject();
                String categoryName = categoryObj.get(JsonConstants.CATEGORY_NAME).getAsString();
                int id = categoryObj.get(JsonConstants.CATEGORY_ID).getAsInt();
                Category category = new Category(categoryName,id);
                categoryArrayList.add(category);
            }
        }
        return categoryArrayList;
    }

    public List<Product> getProduct(String url) throws IOException {
        ArrayList<Product> productArrayList = new ArrayList<>();
        String json = HttpClient.get(url);
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        JsonArray jsonCategories = jsonObject.getAsJsonArray("categories");

        for (int i = 0; i < jsonCategories.size(); i++) {
            JsonObject jsonCat = jsonCategories.get(i).getAsJsonObject();
            String name = getSafeString(jsonCat, JsonConstants.CATEGORY_NAME);
            int id = getSafeInt(jsonCat, JsonConstants.CATEGORY_ID);
            Category category = new Category(name, id);

            JsonArray products = jsonCat.getAsJsonArray("products");
            for (int j = 0; j < products.size(); j++) {
                JsonObject jsonProduct = products.get(j).getAsJsonObject();

                String displayName = getSafeString(jsonProduct, JsonConstants.PRODUCT_NAME);
                String slug = getSafeString(jsonProduct, JsonConstants.SLUG);
                String productId = getSafeString(jsonProduct, JsonConstants.PRODUCT_ID);
                String packaging = getSafeString(jsonProduct, JsonConstants.PACKAGING);
                String shareUrl = getSafeString(jsonProduct, JsonConstants.URL);

                JsonObject priceInstructions = jsonProduct.getAsJsonObject("price_instructions");
                float unitPrice = getSafeFloat(priceInstructions, JsonConstants.UNIT_PRICE);
                double unitSize = getSafeDouble(priceInstructions, JsonConstants.UNIT_SIZE);
                String format = getSafeString(priceInstructions, JsonConstants.FORMAT);

                Product finalProduct = new Product(unitPrice, displayName, category, productId, packaging, shareUrl, unitSize, format, slug);
                productArrayList.add(finalProduct);
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new IOException("Proceso interrumpido durante la espera", e);
            }
        }

        return productArrayList;
    }
}
