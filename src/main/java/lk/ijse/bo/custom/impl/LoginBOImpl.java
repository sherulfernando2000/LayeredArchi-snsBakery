package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.LoginBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.UserDAO;

import java.sql.SQLException;

public class LoginBOImpl implements LoginBO {
    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);
    @Override
    public  boolean checkUserCredential(String userName, String pw) throws SQLException {
        return userDAO.checkCredential(userName,pw);
    }
}
