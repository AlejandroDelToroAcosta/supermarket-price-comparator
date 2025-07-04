package org.vult.sql.query;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws SQLException {
        String keyword = "leche";

        CarrefourQueryService queryService = new CarrefourQueryService("C:\\Users\\aadel\\Desktop\\GCID\\Tercero\\Segundo Cuatrimestre\\BDNR\\fitness-db\\market-comparator\\database.db");
// Paso 1: mostrar productos
        List<String> results = queryService.searchMercadonaByName(keyword);
        System.out.println("Resultados iniciales:");
        results.forEach(System.out::println);

// Paso 2: mostrar categorías disponibles
        Set<String> categories = queryService.getCategoriesForNameSearch_Mercadona(keyword);
        System.out.println("\nCategorías disponibles:");
        categories.forEach(System.out::println);

// Simulación de selección de categoría
        String selectedCategory = "Bebidas vegetales"; // o lo que escoja el usuario

// Paso 3: filtrar por categoría
        System.out.println("\nProductos filtrados por categoría:");
        List<String> filtered = queryService.searchMercadonaByNameAndCategory(keyword, selectedCategory);
        filtered.forEach(System.out::println);


    }
}
