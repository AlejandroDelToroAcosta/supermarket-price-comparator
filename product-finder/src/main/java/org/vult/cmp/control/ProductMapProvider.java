package org.vult.cmp.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.jsoup.Jsoup;
import com.google.gson.Gson;

import org.vult.cmp.utils.JsonConstants;
import org.vult.cmp.model.Category;
import org.vult.cmp.model.Product;

public class ProductMapProvider implements ProductProvider{


    public List<Category> getCategory(String url) throws IOException {

        ArrayList<Category> categoryArrayList = new ArrayList<>();
        String jsonString = Jsoup.connect(url).ignoreContentType(true).execute().body();
        Gson gson = new Gson();
        JsonObject json = gson.fromJson(jsonString, JsonObject.class);
        JsonArray results = json.getAsJsonObject().getAsJsonArray("results");


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

    public List<Product> getProduct(String url) {
        ArrayList<Product> productArrayList = new ArrayList<>();
        return productArrayList;
    }
}
