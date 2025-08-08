package org.vult.api.service.model;


import org.vult.sql.model.MercadonaProductDTO;
import org.vult.sql.model.ProductDTO;

import java.util.HashMap;
import java.util.Map;

public class ProductMapper {
    public static Map<String, Object> mapMercadona(MercadonaProductDTO p) {
        Map<String, Object> item = new HashMap<>();
        item.put("name", p.getName());
        item.put("price", p.getPrice());
        item.put("supermarket", p.getMarketName());
        item.put("imageUrl", p.getImageURL());
        item.put("unitSize", p.getUnitSize());
        return item;
    }

    public static Map<String, Object> mapCarrefour(ProductDTO p) {
        Map<String, Object> item = new HashMap<>();
        item.put("name", p.getName());
        item.put("price", p.getPrice());
        item.put("supermarket", p.getMarketName());
        item.put("imageUrl", p.getImageURL());
        return item;
    }
}
