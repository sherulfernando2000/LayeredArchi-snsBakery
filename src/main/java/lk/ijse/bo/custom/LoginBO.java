package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;

import java.sql.SQLException;

public interface LoginBO extends SuperBO {
    public  boolean checkUserCredential(String userName, String pw) throws SQLException;
}
