package org.example;
import model.ElectronicsProduct;
import service.CsvStorage;
import service.Warehouse;
import util.Warranty;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();
        warehouse.setStorageStrategy(new CsvStorage("products.csv"));
        try {
            warehouse.load();
        } catch (Exception e) {
            System.out.println("Ошибка загрузки файла.");
        }
        Thread monitor = new Thread(new Warranty(warehouse));
        monitor.setDaemon(true);
        monitor.start();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Добавить продукт");
            System.out.println("2. Посмотреть все");
            System.out.println("3. Общая стоимость");
            System.out.println("4. выход");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    try {
                        System.out.print("ID: ");
                        long id = Long.parseLong(scanner.nextLine());

                        System.out.print("Название: ");
                        String name = scanner.nextLine();

                        System.out.print("Цена: ");
                        double price = Double.parseDouble(scanner.nextLine());

                        System.out.print("Кол-во: ");
                        int quantity = Integer.parseInt(scanner.nextLine());

                        System.out.print("Дата окончания гарантии (YYYY-MM-DD): ");
                        LocalDate date = LocalDate.parse(scanner.nextLine());

                        warehouse.addProduct(
                                new ElectronicsProduct(id, name, price, quantity, date)
                        );

                    } catch (Exception e) {
                        System.out.println("Недопустимый ввод.");
                    }
                    break;

                case "2":
                    warehouse.getAllProducts()
                            .forEach(System.out::println);
                    break;

                case "3":
                    System.out.println("Общая стоимость: " +
                            warehouse.getTotalWarehouseValue());
                    break;

                case "4":
                    try {
                        warehouse.save();
                    } catch (Exception e) {
                        System.out.println("Ошибка сохранения");
                    }
                    System.exit(0);
                    break;

                default:
                    System.out.println("Неизвестная команда");
            }
        }
    }
}
