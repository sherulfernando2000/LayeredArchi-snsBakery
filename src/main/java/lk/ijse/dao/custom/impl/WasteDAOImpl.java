package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.WasteDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.entity.Product;
import lk.ijse.entity.Waste;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class WasteDAOImpl implements WasteDAO {
    public  boolean save(List<Product> product) throws SQLException {
        PreparedStatement pstm = null;
        boolean isExecute = false;
        int lastId = getLastId();
        if (lastId != -1) {
            for (int i = 0; i < product.size(); i++) {
                String id = product.get(i).getId();
                int qty = product.get(i).getQty();
                LocalDate now = LocalDate.now();

                String sql = "INSERT INTO wasteManage VALUES(?, ?, ?, ?,?)";
/*
                Connection connection = DbConnection.getInstance().getConnection();
                pstm = connection.prepareStatement(sql);
                pstm.setObject(1, lastId +i + 1);
                pstm.setObject(2, qty);
                pstm.setObject(3, "left overs");
                pstm.setObject(4, id);
                pstm.setObject(5, now);

                isExecute = pstm.executeUpdate() > 0;*/

                isExecute = SQLUtil.execute(sql, lastId +i + 1, qty,"left overs",id,now);

            }

            return isExecute;
        }

        return false;


    }

    public   int getLastId() throws SQLException {


        String sql = "SELECT wastetId FROM wasteManage ";
        /*PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();*/
        ResultSet resultSet = SQLUtil.execute(sql);
        int count = 0;
        while (resultSet.next()) {
            count++;

        }
        return count;
    }

    @Override
    public boolean save(Waste entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Waste entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Waste searchId(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Waste searchTel(String tel) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Waste> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }
}
