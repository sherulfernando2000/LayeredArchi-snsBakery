package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.entity.ProductEmployee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ProductEmployeeDAO extends CrudDAO<ProductEmployee> {
    public  boolean save(ProductEmployee productEmployee) throws SQLException;

    public  List<ProductEmployee> getAll() throws SQLException ;

    public  boolean delete(String eId, String pId, String assignmentType) throws SQLException;
}
