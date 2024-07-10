package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.CustomerBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.CustomerDAO;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Customer);
    @Override
    public  boolean saveCustomer(CustomerDTO customer) throws SQLException,ClassNotFoundException {
        return customerDAO.save(new Customer(customer.getId(), customer.getName(), customer.getTel(),customer.getAddress()));
    }
    @Override
    public  boolean updateCustomer(CustomerDTO customer) throws SQLException,ClassNotFoundException{
        return customerDAO.update(new Customer(customer.getId(), customer.getName(), customer.getTel(),customer.getAddress()));
    }


    @Override
    public  boolean deleteCustomer(String id) throws SQLException,ClassNotFoundException  {
        return customerDAO.delete(id);
    }
    @Override
    public  CustomerDTO searchCustomerId(String id) throws SQLException,ClassNotFoundException{
        Customer customer = customerDAO.searchId(id);
        return new CustomerDTO(customer.getId(),customer.getName(), customer.getTel(),customer.getAddress());
    }
    @Override
    public  CustomerDTO searchCustomerTel(String tel) throws SQLException,ClassNotFoundException{
        Customer customer = customerDAO.searchTel(tel);
        return new CustomerDTO(customer.getId(),customer.getName(), customer.getTel(),customer.getAddress());
    }
    @Override
    public List<CustomerDTO> getAllCustomer() throws SQLException,ClassNotFoundException{

        List<CustomerDTO> customerDTOS = new ArrayList<>();
        List<Customer> customers = customerDAO.getAll();

        for (Customer customer:customers) {
            CustomerDTO customerDTO = new CustomerDTO(customer.getId(),customer.getName(), customer.getTel(),customer.getAddress() );
            customerDTOS.add(customerDTO);
        }
        return customerDTOS;
    }
}
