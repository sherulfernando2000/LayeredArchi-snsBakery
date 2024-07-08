package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.UserDTO;

import java.sql.SQLException;

public interface SignupBO extends SuperBO {
    public  boolean saveUser(UserDTO user) throws SQLException, ClassNotFoundException;

    public  boolean checkUserCredential(String userName, String pw) throws SQLException;
}
