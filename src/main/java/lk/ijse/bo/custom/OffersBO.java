package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.List;

public interface OffersBO extends SuperBO {
    public List<CustomerDTO> getAllCustomer() throws SQLException,ClassNotFoundException;
}
