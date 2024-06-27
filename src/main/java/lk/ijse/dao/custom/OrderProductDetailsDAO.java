package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.OrderProductDetail;

import java.sql.SQLException;
import java.util.List;

public interface OrderProductDetailsDAO extends CrudDAO<OrderProductDetail> {
    public  boolean save(List<OrderProductDetail> odList) throws SQLException;

    public   boolean save(OrderProductDetail od) throws SQLException;
}
