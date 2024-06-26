package lk.ijse.repository;

import lk.ijse.db.DbConnection;
import lk.ijse.model.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRepo {
    public static String getCurrentId() throws SQLException {
        //String sql = "SELECT orderId FROM orders ORDER BY orderId DESC LIMIT 1";
        String sql = "SELECT CONCAT('O-', MAX(CAST(SUBSTRING_INDEX(orderId, '-', -1) AS UNSIGNED))) AS max_order_id FROM orders;\n";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            String orderId = resultSet.getString(1);
            return orderId;
        }
        return null;
    }

    public static boolean save(Order order) throws SQLException {
        String sql = "INSERT INTO orders VALUES(?, ?, ?,?,?)";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setString(1, order.getOrderId());
        pstm.setString(2, order.getStatus());
        pstm.setDate(3, order.getDate());
        pstm.setDouble(4,order.getTotalAmount());
        pstm.setString(5,order.getCustomerId());

        return pstm.executeUpdate() > 0;
    }

    public static int getOderCount() throws SQLException {
        String sql = "SELECT orderId FROM orders ";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        int count = 0;
        while (resultSet.next()) {
            count++;

        }
        return count;
    }
}