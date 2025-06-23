package org.vult.mrcdn.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.vult.mrcdn.model.Product;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class Serialize {
    public void serializeProducts(List<Product> products, String filePath) {
        try (Writer writer = new FileWriter(filePath)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(products, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<Product> deserializeProducts(String filePath) {
        try (Reader reader = new FileReader(filePath)) {
            Gson gson = new Gson();
            Type productListType = new TypeToken<List<Product>>() {}.getType();
            return gson.fromJson(reader, productListType);
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

}
