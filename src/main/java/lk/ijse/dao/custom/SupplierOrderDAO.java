package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.entity.SupplierOrder;

import java.sql.Date;
import java.sql.SQLException;

public interface SupplierOrderDAO extends CrudDAO<SupplierOrder> {
    public  boolean detele(String sId, String iId, Date date) throws SQLException;
}
