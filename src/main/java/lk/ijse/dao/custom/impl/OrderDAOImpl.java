package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.OrderDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.entity.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public  String getCurrentId() throws SQLException {

        String sql = "SELECT CONCAT('O-', MAX(CAST(SUBSTRING_INDEX(orderId, '-', -1) AS UNSIGNED))) AS max_order_id FROM orders;\n";
        ResultSet resultSet = SQLUtil.execute(sql);
        if(resultSet.next()) {
            String orderId = resultSet.getString(1);
            return orderId;
        }
        return null;
    }

    @Override
    public  boolean save(Order order) throws SQLException {
        String sql = "INSERT INTO orders VALUES(?, ?, ?,?,?)";
        return SQLUtil.execute(sql,order.getOrderId(),order.getStatus(),order.getDate(),order.getTotalAmount(),order.getCustomerId());

    }


    @Override
    public  int getOderCount() throws SQLException {
        String sql = "SELECT orderId FROM orders ";
        ResultSet resultSet = SQLUtil.execute(sql);
        int count = 0;
        while (resultSet.next()) {
            count++;

        }
        return count;
    }

    @Override
    public boolean update(Order entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Order searchId(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Order searchTel(String tel) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Order> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

}
