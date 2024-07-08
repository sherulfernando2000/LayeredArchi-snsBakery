package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.SupplierOrderBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.IngredientDAO;
import lk.ijse.dao.custom.SupplierDAO;
import lk.ijse.dao.custom.SupplierOrderDAO;
import lk.ijse.dto.SupplierOrderDTO;
import lk.ijse.entity.SupplierOrder;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierOrderBOImpl implements SupplierOrderBO {
    SupplierOrderDAO supplierOrderDAO = (SupplierOrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER_ORDER);
    IngredientDAO ingredientDAO = (IngredientDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.INGREDIENT);
    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);
    public  boolean saveSupplierOrder(SupplierOrderDTO dto) throws SQLException, ClassNotFoundException {
       return supplierOrderDAO.save(new SupplierOrder(dto.getIngredientId(),dto.getSupplierId(),dto.getDate(),dto.getQty(),dto.getPrice(),dto.getTotal()));
    }


    public List<SupplierOrderDTO> getAllSupplierOrder() throws SQLException, ClassNotFoundException {
        List<SupplierOrder> supplierOrders = supplierOrderDAO.getAll();
        List<SupplierOrderDTO> supplierOrderDTOS = new ArrayList<>();

        for (SupplierOrder supplierOrder:supplierOrders) {
            supplierOrderDTOS.add(new SupplierOrderDTO(supplierOrder.getIngredientId(),supplierOrder.getSupplierId(),supplierOrder.getDate(),supplierOrder.getQty(),supplierOrder.getPrice(),supplierOrder.getTotal()));
        }
        return supplierOrderDTOS;

    }

    public  boolean deteleSupplierOrder(String sId, String iId, Date date) throws SQLException {
       return supplierOrderDAO.detele(sId,iId,date);

    }

    @Override
    public  List<String> getIngredientName() throws SQLException {
        List<String> ingredients = ingredientDAO.getName();

        List<String> nameList = new ArrayList<>();
        for (String ingredient:ingredients) {
            nameList.add(ingredient);
        }
        return nameList;
    }
    @Override
    public  List<String> getSupplierName() throws SQLException {
        return supplierDAO.getName();
    }
}
