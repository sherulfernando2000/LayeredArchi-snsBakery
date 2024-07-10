package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.SupplierOrderDTO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface SupplierOrderBO extends SuperBO {
    public  boolean saveSupplierOrder(SupplierOrderDTO dto) throws SQLException, ClassNotFoundException;


    public List<SupplierOrderDTO> getAllSupplierOrder() throws SQLException, ClassNotFoundException;

    public  boolean deteleSupplierOrder(String sId, String iId, Date date) throws SQLException;

    public  List<String> getIngredientName() throws SQLException;

    public  List<String> getSupplierName() throws SQLException;
}
