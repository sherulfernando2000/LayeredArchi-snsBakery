package lk.ijse.dao.custom.impl;

import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.PaymentDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.entity.Payment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public  String getCurrentPaymentId() throws SQLException {
        String sql = "SELECT CONCAT('P-', MAX(CAST(SUBSTRING_INDEX(paymentId, '-', -1) AS UNSIGNED))) AS max_payment_id FROM payment;\n";
        /*PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();*/
        ResultSet resultSet = SQLUtil.execute(sql);
        if(resultSet.next()) {
            String paymentId = resultSet.getString(1);
            return paymentId;
        }
        return null;
    }

    @Override
    public  boolean save(Payment payment) throws SQLException {
        String sql = "INSERT INTO payment VALUES(?, ?, ?, ?,?,?,?,?)";

        return SQLUtil.execute(sql,payment.getPaymentId(),payment.getPaymentMethod(),payment.getDate(),payment.getDiscountAmount(),payment.getTotalAmount(),payment.getOrderId(),payment.getDiscountType(),payment.getDiscountPrecentage());



    }

    @Override
    public boolean update(Payment entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Payment searchId(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Payment searchTel(String tel) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Payment> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }
}
