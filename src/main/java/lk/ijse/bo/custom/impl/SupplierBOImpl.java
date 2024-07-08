package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.SupplierBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.SupplierDAO;
import lk.ijse.dto.SupplierDTO;
import lk.ijse.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierBOImpl implements SupplierBO {
    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);
    @Override
    public  boolean saveSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.save(new Supplier(dto.getId(), dto.getName(), dto.getTel(), dto.getAddress()));
    }

    @Override
    public  boolean updateSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(new Supplier(dto.getId(), dto.getName(), dto.getTel(), dto.getAddress()));

    }
    @Override
    public  boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(id);
    }
    @Override
    public  SupplierDTO searchSupplierId(String id) throws SQLException, ClassNotFoundException {
       Supplier supplier = supplierDAO.searchId(id);
       return new SupplierDTO(supplier.getId(), supplier.getName(), supplier.getTel(), supplier.getAddress());

    }

    @Override
    public SupplierDTO searchSupplierTel(String tel) throws SQLException, ClassNotFoundException {
        return null;
    }
    @Override
    public List<SupplierDTO> getAllSupplier() throws SQLException, ClassNotFoundException {
        List<Supplier> suppliers = supplierDAO.getAll();
        List<SupplierDTO> supplierDTOS = new ArrayList<>();
        for (Supplier supplier:suppliers) {
            supplierDTOS.add(new SupplierDTO(supplier.getId(), supplier.getName(), supplier.getTel(), supplier.getAddress()));
        }
    return supplierDTOS;
    }
    @Override
    public  List<String> getSupplierName() throws SQLException {
            return supplierDAO.getName();
    }
    @Override
    public  SupplierDTO searchSupplierByName(String nameValue) throws SQLException {
        Supplier supplier = supplierDAO.searchByName(nameValue);
        return new SupplierDTO(supplier.getId(), supplier.getName(), supplier.getTel(), supplier.getAddress());
    }
    @Override
    public  String getSupplierName(String supplierId) throws SQLException {
        return supplierDAO.getName(supplierId);
    }

}
