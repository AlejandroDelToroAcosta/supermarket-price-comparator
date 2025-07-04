package org.vult.sql.query;

import java.sql.SQLException;
import java.util.*;

public class MercadonaCLI {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MercadonaQueryService queryService = new MercadonaQueryService("C:\\Users\\aadel\\Desktop\\GCID\\Tercero\\Segundo Cuatrimestre\\BDNR\\fitness-db\\market-comparator\\database.db");

        System.out.println("🔍 Buscador de productos Mercadona");
        System.out.print("Introduce una palabra clave (ej. leche): ");
        String keyword = scanner.nextLine();

        try {
            // Paso 1: Mostrar resultados por nombre
            List<String> products = queryService.searchMercadonaByName(keyword);
            System.out.println("\nResultados encontrados:");
            for (int i = 0; i < products.size(); i++) {
                System.out.printf("%d. %s%n", i + 1, products.get(i));
            }

            // Paso 2: Mostrar categorías disponibles
            Set<String> categories = queryService.getCategoriesForNameSearch_Mercadona(keyword);
            if (categories.isEmpty()) {
                System.out.println("No hay categorías disponibles para los resultados.");
                return;
            }

            System.out.println("\nCategorías disponibles:");
            List<String> categoryList = new ArrayList<>(categories);
            for (int i = 0; i < categoryList.size(); i++) {
                System.out.printf("%d. %s%n", i + 1, categoryList.get(i));
            }

            // Paso 3: Selección de categoría
            System.out.print("\nElige una categoría por número: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // limpiar el buffer
            if (choice < 1 || choice > categoryList.size()) {
                System.out.println("Selección inválida.");
                return;
            }
            String selectedCategory = categoryList.get(choice - 1);

            // Paso 4: Mostrar productos filtrados
            List<String> filtered = queryService.searchMercadonaByNameAndCategory(keyword, selectedCategory);
            System.out.println("\nProductos filtrados por categoría '" + selectedCategory + "':");
            filtered.forEach(System.out::println);

        } catch (SQLException e) {
            System.err.println("Error al consultar la base de datos: " + e.getMessage());
        }
    }
}
