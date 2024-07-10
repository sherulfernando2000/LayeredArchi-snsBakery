package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.CustomerDAO;
import lk.ijse.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public  boolean save(Customer customer) throws SQLException,ClassNotFoundException {
        String sql = "INSERT INTO customer VALUES (?,?,?,?)";
        return SQLUtil.execute(sql,customer.getId(),customer.getName(),customer.getTel(),customer.getAddress());

    }
    @Override
    public  boolean update(Customer customer) throws SQLException,ClassNotFoundException {
        String sql = "UPDATE customer SET cName = ?, email = ?, phoneNo = ? WHERE customerId = ?";
        return SQLUtil.execute(sql,customer.getName(),customer.getAddress(),customer.getTel(),customer.getId());

    }


    @Override
    public  boolean delete(String id) throws SQLException,ClassNotFoundException  {
        String sql = "DELETE FROM customer WHERE customerId = ?";
        return SQLUtil.execute(sql,id);
    }
    @Override
    public  Customer searchId(String id) throws SQLException,ClassNotFoundException  {
        String sql = "SELECT * FROM customer WHERE customerId = ?";
        ResultSet resultSet = SQLUtil.execute(sql,id);
        resultSet.next();
            String cus_id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String tel = resultSet.getString(3);
            String address = resultSet.getString(4);

            return new Customer(cus_id, name, tel, address);





    }
    @Override
    public  Customer searchTel(String tel) throws SQLException,ClassNotFoundException  {
        String sql = "SELECT * FROM customer WHERE phoneNo= ?";
        ResultSet resultSet = SQLUtil.execute(sql,tel);
        if (resultSet.next()) {
            String cus_id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String cus_tel = resultSet.getString(3);
            String address = resultSet.getString(4);

            Customer customer = new Customer(cus_id, name, cus_tel, address);
            return customer;
        }
        return null;

    }
    @Override
    public  List<Customer> getAll() throws SQLException,ClassNotFoundException   {
        String sql = "SELECT * FROM customer";


        ResultSet resultSet = SQLUtil.execute(sql);
        List<Customer> cusList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String tel = resultSet.getString(3);
            String address = resultSet.getString(4);

            Customer customer = new Customer(id, name, tel, address);
            cusList.add(customer);
        }
        return cusList;

    }
}
