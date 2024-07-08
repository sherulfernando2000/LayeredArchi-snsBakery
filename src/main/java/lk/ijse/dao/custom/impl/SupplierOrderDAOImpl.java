package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.SupplierOrderDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.entity.SupplierOrder;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierOrderDAOImpl implements SupplierOrderDAO {
    @Override
    public  boolean save(SupplierOrder supplierOrder) throws SQLException {
        String sql = "INSERT INTO supplyOrder VALUES (?,?,?,?,?,?)";
        return SQLUtil.execute(sql,supplierOrder.getIngredientId(),supplierOrder.getSupplierId(),supplierOrder.getDate(),supplierOrder.getQty(),supplierOrder.getPrice(),supplierOrder.getTotal());
       /* PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,supplierOrder.getIngredientId());
        pstm.setObject(2,supplierOrder.getSupplierId());
        pstm.setObject(3,supplierOrder.getDate());
        pstm.setObject(4,supplierOrder.getQty());
        pstm.setObject(5,supplierOrder.getPrice());
        pstm.setObject(6,supplierOrder.getTotal());


        return pstm.executeUpdate()>0 ;*/
    }

    @Override
    public  List<SupplierOrder> getAll() throws SQLException {
        String sql = "SELECT * FROM supplyOrder";
        ResultSet resultSet = SQLUtil.execute(sql);

        /*PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
*/
        List<SupplierOrder> orderList = new ArrayList<>();

        while (resultSet.next()) {
            String ingredientId = resultSet.getString(1);
            String supplierId = resultSet.getString(2);
            Date date = Date.valueOf(resultSet.getString(3));
            int qty = resultSet.getInt(4);
            double price = Double.valueOf(resultSet.getString(5));
            double total = Double.parseDouble(resultSet.getString(6));



            SupplierOrder supplierOrder = new SupplierOrder(ingredientId,supplierId,date,qty,price,total);
            orderList.add(supplierOrder);
        }
        return orderList;

    }
    @Override
    public  boolean detele(String sId, String iId, Date date) throws SQLException {
        String sql = "DELETE FROM supplyOrder WHERE supplierId = ? AND ingredientId = ? AND  date = ? ;\n";
        return SQLUtil.execute(sql,sId,iId,date);
        /*PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,sId);
        pstm.setObject(2,iId);
        pstm.setObject(3,date);

        return pstm.executeUpdate()>0;*/

    }

    @Override
    public boolean update(SupplierOrder entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public SupplierOrder searchId(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public SupplierOrder searchTel(String tel) throws SQLException, ClassNotFoundException {
        return null;
    }

}
