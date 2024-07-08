package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SupplierDAO extends CrudDAO<Supplier> {

    public List<String> getName() throws SQLException;

    public  Supplier searchByName(String nameValue) throws SQLException;

    public  String getName(String supplierId) throws SQLException;
}
