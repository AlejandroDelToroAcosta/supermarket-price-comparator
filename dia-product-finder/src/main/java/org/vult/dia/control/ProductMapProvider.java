package org.vult.dia.control;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.vult.dia.model.Category;
import org.vult.dia.model.Product;
import org.vult.dia.utils.HTTPClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductMapProvider {
    public List<Category> getCategory(String url) throws IOException {
        String json = HTTPClient.get(url);
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);

        JsonObject menuAnalytics = jsonObject.getAsJsonObject("menu_analytics");

        List<Category> categories = new ArrayList<>();
        for (Map.Entry<String, JsonElement> entry : menuAnalytics.entrySet()) {
            JsonObject section = entry.getValue().getAsJsonObject();
            extractDiaCategoriesRecursively(section, categories);
        }
        return categories;
    }

    private void extractDiaCategoriesRecursively(JsonObject node, List<Category> categories) {
        if (node.has("parameter") && node.has("path")) {
            String parameter = node.get("parameter").getAsString();
            String path = node.get("path").getAsString();
            categories.add(new Category(path, parameter));  // Usa tu propia clase Category
        }

        if (node.has("children")) {
            JsonObject children = node.getAsJsonObject("children");
            for (Map.Entry<String, JsonElement> childEntry : children.entrySet()) {
                JsonObject childNode = childEntry.getValue().getAsJsonObject();
                extractDiaCategoriesRecursively(childNode, categories);
            }
        }
    }

    public void getProduct(Category category) throws IOException{
        List<Product> products = new ArrayList<>();

        String url = "https://www.dia.es/api/v1/plp-back/reduced/?navigation=" + category.getPath();

        String json = HTTPClient.get(url);
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
    }
}
