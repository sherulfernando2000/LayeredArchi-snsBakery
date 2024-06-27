package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Product;
import lk.ijse.entity.OrderProductDetail;

import java.sql.SQLException;
import java.util.List;

public interface ProductDAO extends CrudDAO<Product> {

    public  List<String> getDescription() throws SQLException;

    public  Product searchByDescription(String description) throws SQLException;

    public  boolean update(List<OrderProductDetail> odList) throws SQLException;

    public   boolean updateQty(String productId, int qty) throws SQLException;

    public  List<String> getName() throws SQLException;

    public  String getName(String productId) throws SQLException;

    public  Product searchByName(String nameValue) throws SQLException;
}
