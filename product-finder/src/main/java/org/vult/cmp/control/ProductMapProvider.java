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
        JsonArray results = json.getAsJsonArray("results");


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
        String jsonString = Jsoup.connect(url).ignoreContentType(true).execute().body();
        Gson gson = new Gson();
        JsonObject json = gson.fromJson(jsonString, JsonObject.class);
        JsonArray jsonCategories = json.getAsJsonObject().getAsJsonArray("categories");

        for (int i=0; i< jsonCategories.size(); i++) {
            JsonObject jsonCat = jsonCategories.get(i).getAsJsonObject();
            String name = jsonCat.get(JsonConstants.CATEGORY_NAME).getAsString();
            int id = jsonCat.get(JsonConstants.CATEGORY_ID).getAsInt();
            Category category = new Category(name,id);

            JsonArray products = jsonCat.getAsJsonArray("products");
            for (int j = 0; j< products.size();j++) {

                JsonObject jsonProduct = products.get(j).getAsJsonObject();
                String displayName = jsonProduct.get(JsonConstants.PRODUCT_NAME).getAsString();
                JsonObject priceInstructions = jsonProduct.getAsJsonObject("price_instructions");
                float unitPrice = Float.parseFloat(priceInstructions.get(JsonConstants.UNIT_PRICE).getAsString());

                Product finalProduct = new Product(unitPrice,displayName,category);
                productArrayList.add(finalProduct);
            }
        }

        return productArrayList;
    }
}
