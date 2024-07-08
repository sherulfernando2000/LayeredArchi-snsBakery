package lk.ijse.bo.custom.impl;

import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import lk.ijse.bo.custom.DashBoardBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.*;
import lk.ijse.dto.*;
import lk.ijse.entity.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DashBoardBOImpl implements DashBoardBO {
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY);
    ProductDAO productDAO = (ProductDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PRODUCT);

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Customer);

    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);

    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);


    public List<CustomMostSellItemDTO> getMostSellItem() throws SQLException{
        List<CustomMostSellItemDTO> mostSellItemDTOS = new ArrayList<>();
        List<CustomMostSellItem> mostSellItems = queryDAO.getMostSellItem();
        for (CustomMostSellItem mostSellItem:mostSellItems) {
            mostSellItemDTOS.add(new CustomMostSellItemDTO(mostSellItem.getName(),mostSellItem.getQty()));
        }
       return mostSellItemDTOS;
    }


    public  double getDailyRevenue(String text) throws SQLException {
        return queryDAO.getDailyRevenue(text);
    }


    public  int getProductSold(String desc) throws SQLException {
        return queryDAO.getProductSold(desc);
    }

    public XYChart.Series getDateCount() throws SQLException {
        //return queryDAO.getDateCount();

        XYChart.Series series1 = new XYChart.Series();  // represent a series of data points on the chart.
        series1.setName("Bakery");
        List<CustomDTO> dailyRevenueList = new ArrayList<>();/*DashboardRepo.getDateCount();*/
        try {
            List<CustomEntity> customEntities = queryDAO.getDateCount();
            for (CustomEntity customEntity:customEntities) {
                dailyRevenueList.add(new CustomDTO(customEntity.getDate(),customEntity.getCount()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        for (CustomDTO dailyRevenue: dailyRevenueList) {
            series1.getData().add(new XYChart.Data<>(dailyRevenue.getDate(),dailyRevenue.getCount()));  //xy chart class eke thiyana static innerclass ekak
        }
        return series1;
    }

    public  List<String> getProductDescription() throws SQLException {
        return productDAO.getDescription();

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

    @Override
    public  int getOderCount() throws SQLException, ClassNotFoundException {
        return orderDAO.getOderCount();

    }

    @Override
    public  List<ProductDTO> getAllProduct() throws SQLException, ClassNotFoundException {
        List<Product> products = productDAO.getAll();
        List<ProductDTO> proList = new ArrayList<>();

        for (Product product:products) {
            proList.add(new ProductDTO(product.getId(), product.getName(),product.getCategory(),product.getQty(),product.getPrice()));
        }
        return proList;
    }
    @Override
    public List<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException {
        List<Employee> employees = employeeDAO.getAll();
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();

        for (Employee employee:employees) {
            employeeDTOS.add(new EmployeeDTO(employee.getEmployeeId(),employee.getEmployeeName(),employee.getEmployeeAddress(),employee.getEmployeeTel(),employee.getEmployeePosition(),employee.getEmployeeSalary()));
        }
        return employeeDTOS;
    }

    @Override
    public  double getDailyRevenue() throws SQLException{
        return queryDAO.getDailyRevenue();
    }
    @Override
    public  double getMonthlyRevenue() throws SQLException{
        return queryDAO.getMonthlyRevenue();
    }


}
