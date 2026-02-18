package service;

import model.AbstractProduct;
import model.ElectronicsProduct;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CsvStorage implements FileStorage {

    private final String fileName;

    public CsvStorage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void save(List<AbstractProduct> products) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (AbstractProduct product : products) {
                writer.write(product.toString());
                writer.newLine();
            }
        }
    }

    @Override
    public List<AbstractProduct> load() throws IOException {
        List<AbstractProduct> products = new ArrayList<>();

        File file = new File(fileName);
        if (!file.exists()) {
            return products;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                long id = Long.parseLong(parts[0]);
                String name = parts[1];
                double price = Double.parseDouble(parts[2]);
                int quantity = Integer.parseInt(parts[3]);
                LocalDate warrantyDate = LocalDate.parse(parts[4]);

                products.add(new ElectronicsProduct(id, name, price, quantity, warrantyDate));
            }
        }

        return products;
    }
}