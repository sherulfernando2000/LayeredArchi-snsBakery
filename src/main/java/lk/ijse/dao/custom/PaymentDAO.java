package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.SuperDAO;
import lk.ijse.entity.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface PaymentDAO extends CrudDAO<Payment> {
    public  String getCurrentPaymentId() throws SQLException;


}
