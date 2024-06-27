package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.OrderProductDetailsDAO;
import lk.ijse.entity.OrderProductDetail;

import java.sql.SQLException;
import java.util.List;

public class OrderProductDetailsDAOImpl implements OrderProductDetailsDAO {
    public  boolean save(List<OrderProductDetail> odList) throws SQLException {
        for (OrderProductDetail od : odList) {
            boolean isSaved = save(od);
            if(!isSaved) {
                return false;
            }
        }
        return true;
    }

    public   boolean save(OrderProductDetail od) throws SQLException {
        String sql = "INSERT INTO orderProductDetail VALUES(?, ?, ?, ?,?)";
       return SQLUtil.execute(sql,od.getOrderId(),od.getProductId(),od.getUnitPrice(),od.getQty(),od.getTotal());
    }

    @Override
    public boolean update(OrderProductDetail entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public OrderProductDetail searchId(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public OrderProductDetail searchTel(String tel) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<OrderProductDetail> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }
}
