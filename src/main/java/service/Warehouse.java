package service;

import model.AbstractProduct;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Warehouse{

    private final Map<Long, AbstractProduct> productMap;
    private FileStorage storageStrategy;

    public Warehouse() {
        productMap = new HashMap<>();
    }


    public void setStorageStrategy(FileStorage storageStrategy) {
        this.storageStrategy = storageStrategy;
    }

    public void addProduct(AbstractProduct product) {
        productMap.put(product.getId(), product);
    }

    public void removeProduct(long id) {
        productMap.remove(id);
    }

    public Optional<AbstractProduct> findById(long id) {
        return Optional.ofNullable(productMap.get(id));
    }

    public List<AbstractProduct> getAllProducts() {
        return new ArrayList<>(productMap.values());
    }

    public List<AbstractProduct> filterByPrice(double minPrice) {
        return productMap.values()
                .stream()
                .filter(p -> p.getPrice() >= minPrice)
                .collect(Collectors.toList());
    }

    public double getTotalWarehouseValue() {
        return productMap.values()
                .stream()
                .mapToDouble(AbstractProduct::getTotalValue)
                .sum();
    }

    public void save() throws IOException {
        storageStrategy.save(getAllProducts());
    }

    public void load() throws IOException {
        List<AbstractProduct> products = storageStrategy.load();
        products.forEach(p -> productMap.put(p.getId(), p));
    }
    public void clear() {
        productMap.clear();
    }
}