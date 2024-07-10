package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBO extends SuperBO {

    public  boolean saveCustomer(CustomerDTO customer) throws SQLException,ClassNotFoundException;

    public  boolean updateCustomer(CustomerDTO customer) throws SQLException,ClassNotFoundException;



    public  boolean deleteCustomer(String id) throws SQLException,ClassNotFoundException;

    public  CustomerDTO searchCustomerId(String id) throws SQLException,ClassNotFoundException;

    public  CustomerDTO searchCustomerTel(String tel) throws SQLException,ClassNotFoundException;

    public List<CustomerDTO> getAllCustomer() throws SQLException,ClassNotFoundException;
}
