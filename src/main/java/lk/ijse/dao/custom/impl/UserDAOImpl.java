package lk.ijse.dao.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.UserDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    public  boolean save(User user)  throws SQLException {
        String sql = "INSERT INTO user VALUES(?, ?, ?, ?)";
        return SQLUtil.execute(sql,user.getUserName(),user.getPassword(),user.getPhoneNo(),user.getRole());

        /*Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,user.getUserName());
        pstm.setObject(2,user.getPassword());
        pstm.setObject(3,user.getPhoneNo());
        pstm.setObject(4,user.getRole());

        return pstm.executeUpdate()>0;*/


    }

    public  boolean checkCredential(String userName, String pw) throws SQLException {
        String sql = "SELECT userName, password FROM user WHERE userName = ?";

        /*Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, userName);

        ResultSet resultSet = pstm.executeQuery();*/
        ResultSet resultSet = SQLUtil.execute(sql,userName);
        if(resultSet.next()) {
            String dbPw = resultSet.getString("password");

            if(pw.equals(dbPw)) {
                return true;
                // navigateToTheDashboard();
            } else {

                new Alert(Alert.AlertType.ERROR, "sorry! password is incorrect!").show();
                return false;
            }

        } else {
            new Alert(Alert.AlertType.INFORMATION, "sorry! user name can't be find!").show();
            return false;
        }
    }

    @Override
    public boolean update(User entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public User searchId(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public User searchTel(String tel) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<User> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }
}

