package service;
import model.AbstractProduct;
import java.io.IOException;
import java.util.List;

public interface FileStorage {

    void save(List<AbstractProduct> products) throws IOException;

    List<AbstractProduct> load() throws IOException;
}
