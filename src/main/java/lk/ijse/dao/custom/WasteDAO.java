package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Product;
import lk.ijse.entity.Waste;

import java.sql.SQLException;
import java.util.List;

public interface WasteDAO extends CrudDAO<Waste> {
    public  boolean save(List<Product> product) throws SQLException;
}
