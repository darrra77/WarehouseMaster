package model;

import java.time.LocalDate;
public class ElectronicsProduct extends AbstractProduct {
    private LocalDate warrantyEndDate;

    public ElectronicsProduct(long id, String name, double price, int quantity, LocalDate warrantyEndDate) {
        super(id, name, price, quantity);
        this.warrantyEndDate = warrantyEndDate;
    }

    public LocalDate getWarrantyEndDate() {
        return warrantyEndDate;
    }

    public boolean isWarrantyExpired() {
        return LocalDate.now().isAfter(warrantyEndDate);
    }

    @Override
    public String toString() {
        return super.toString() + "," + warrantyEndDate;
    }
}