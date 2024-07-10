package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.EmployeeDTO;
import lk.ijse.dto.ProductDTO;
import lk.ijse.dto.ProductEmployeeDTO;

import java.sql.SQLException;
import java.util.List;

public interface ProductEmployeeBO extends SuperBO {
    public  boolean saveProductEmployee(ProductEmployeeDTO dto) throws SQLException;

    public List<ProductEmployeeDTO> getAllProductEmployee() throws SQLException;

    public  boolean deleteProductEmployee(String eId, String pId, String assignmentType) throws SQLException;


    public  List<String> getEmployeeName() throws SQLException;

    public  List<String> getProductName() throws SQLException;

    public EmployeeDTO searchEmployeeByName(String nameValue) throws SQLException;

    public ProductDTO searchProductByName(String nameValue) throws SQLException;

}
