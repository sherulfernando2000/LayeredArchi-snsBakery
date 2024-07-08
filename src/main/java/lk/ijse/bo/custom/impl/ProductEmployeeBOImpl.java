package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.ProductEmployeeBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.EmployeeDAO;
import lk.ijse.dao.custom.ProductDAO;
import lk.ijse.dao.custom.ProductEmployeeDAO;
import lk.ijse.dto.EmployeeDTO;
import lk.ijse.dto.ProductDTO;
import lk.ijse.dto.ProductEmployeeDTO;
import lk.ijse.entity.Employee;
import lk.ijse.entity.Product;
import lk.ijse.entity.ProductEmployee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductEmployeeBOImpl implements ProductEmployeeBO {

    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);
    ProductEmployeeDAO productEmployeeDAO = (ProductEmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PRODUCT_EMPLOYEE);
    ProductDAO productDAO = (ProductDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PRODUCT);
    public  boolean saveProductEmployee(ProductEmployeeDTO dto) throws SQLException {
        return productEmployeeDAO.save(new ProductEmployee(dto.getEmployeeId(), dto.getProductId(), dto.getAssignmentType()));
    }

    public  List<ProductEmployeeDTO> getAllProductEmployee() throws SQLException {
        List<ProductEmployee> productEmployees = productEmployeeDAO.getAll();
        List<ProductEmployeeDTO> productEmployeeDTOS = new ArrayList<>();

        for (ProductEmployee productEmployee:productEmployees) {
            productEmployeeDTOS.add(new ProductEmployeeDTO(productEmployee.getEmployeeId(),productEmployee.getProductId(),productEmployee.getAssignmentType()));

        }
        return productEmployeeDTOS;
    }

    public  boolean deleteProductEmployee(String eId, String pId, String assignmentType) throws SQLException {
        return productEmployeeDAO.delete(eId,pId,assignmentType);
    }

    public  List<String> getEmployeeName() throws SQLException {
        return employeeDAO.getName();
    }

    public  List<String> getProductName() throws SQLException {
        return productDAO.getName();

    }

    public EmployeeDTO searchEmployeeByName(String nameValue) throws SQLException{
        Employee employee = employeeDAO.searchByName(nameValue);
        return new EmployeeDTO(employee.getEmployeeId(),employee.getEmployeeName(),employee.getEmployeeAddress(),employee.getEmployeeTel(),employee.getEmployeePosition(),employee.getEmployeeSalary());
    }

    public ProductDTO searchProductByName(String nameValue) throws SQLException {
        Product product = productDAO.searchByName(nameValue);
        return new ProductDTO(product.getId(), product.getName(),product.getCategory(),product.getQty(),product.getPrice());

    }

}
