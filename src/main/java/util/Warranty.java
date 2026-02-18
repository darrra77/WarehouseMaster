package util;

import model.ElectronicsProduct;
import service.Warehouse;

public class Warranty implements Runnable {

    private final Warehouse warehouse;

    public Warranty(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(30000);

                warehouse.getAllProducts()
                        .stream()
                        .filter(p -> p instanceof ElectronicsProduct)
                        .map(p -> (ElectronicsProduct) p)
                        .filter(ElectronicsProduct::isWarrantyExpired)
                        .forEach(p ->
                                System.out.println("гарантия истекла: " + p.getName())
                        );

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}