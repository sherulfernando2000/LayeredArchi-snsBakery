package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.SupplierDTO;
import lk.ijse.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SupplierBO extends SuperBO {

    public  boolean saveSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException;


    public  boolean updateSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException;

    public  boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException;

    public  SupplierDTO searchSupplierId(String id) throws SQLException, ClassNotFoundException;


    public SupplierDTO searchSupplierTel(String tel) throws SQLException, ClassNotFoundException;

    public List<SupplierDTO> getAllSupplier() throws SQLException, ClassNotFoundException;

    public  List<String> getSupplierName() throws SQLException;

    public  SupplierDTO searchSupplierByName(String nameValue) throws SQLException;

    public  String getSupplierName(String supplierId) throws SQLException;

}
