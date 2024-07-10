package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.OffersBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.CustomerDAO;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OffersBOImpl implements OffersBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Customer);
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
