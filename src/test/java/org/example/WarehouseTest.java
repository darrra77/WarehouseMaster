package org.example;
import model.ElectronicsProduct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.Warehouse;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class WarehouseTest {

    private Warehouse warehouse;
    @BeforeEach
    void setUp() {
        warehouse = new Warehouse();
        warehouse.clear();
    }

    @Test
    void testAddProduct() {
        ElectronicsProduct product =
                new ElectronicsProduct(1, "телевизор", 1000, 3, LocalDate.now());
        warehouse.addProduct(product);
        assertTrue(warehouse.findById(1).isPresent());
    }

    @Test
    void testRemoveProduct() {
        ElectronicsProduct product =
                new ElectronicsProduct(1, "телевизор", 1000, 3, LocalDate.now());
        warehouse.addProduct(product);
        warehouse.removeProduct(1);
        assertFalse(warehouse.findById(1).isPresent());
    }

    @Test
    void testTotalValue() {
        ElectronicsProduct product =
                new ElectronicsProduct(2, "пк", 1000, 1, LocalDate.now());
        warehouse.addProduct(product);
        assertEquals(1000, warehouse.getTotalWarehouseValue());
    }

    @Test
    void testFilterByPrice() {
        ElectronicsProduct cheap = new ElectronicsProduct(1, "клавиатура", 50, 1, LocalDate.now());
        ElectronicsProduct expensive = new ElectronicsProduct(2, "пк", 1000, 1, LocalDate.now());
        warehouse.addProduct(cheap);
        warehouse.addProduct(expensive);
        assertEquals(1, warehouse.filterByPrice(100).size());
    }

    @Test
    void testWarrantyExpired() {
        ElectronicsProduct product =
                new ElectronicsProduct(3, "самсунг", 300, 1,
                        LocalDate.now().minusDays(1));

        assertTrue(product.isWarrantyExpired());
    }
}