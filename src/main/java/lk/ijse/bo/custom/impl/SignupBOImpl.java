package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.SignupBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.UserDAO;
import lk.ijse.dto.UserDTO;
import lk.ijse.entity.User;

import java.sql.SQLException;

public class SignupBOImpl implements SignupBO {
    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);
    public  boolean saveUser(UserDTO user) throws SQLException, ClassNotFoundException {

        return userDAO.save(new User(user.getUserName(),user.getPassword(),user.getPhoneNo(),user.getRole()));
    }

    public  boolean checkUserCredential(String userName, String pw) throws SQLException {
        return userDAO.checkCredential(userName,pw);
    }
}
