package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeBO extends SuperBO {
    public  boolean saveEmployee(EmployeeDTO employee) throws SQLException, ClassNotFoundException;

    public  boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException;

    public  boolean updateEmployee(EmployeeDTO employee) throws SQLException, ClassNotFoundException;

    public  EmployeeDTO searchEmployeeId(String id) throws SQLException, ClassNotFoundException;


    public EmployeeDTO searchEmployeeTel(String tel) throws SQLException, ClassNotFoundException;

    public List<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException;

    public  List<String> getEmployeeName() throws SQLException;

    public  String getEmployeeName(String employeeId) throws SQLException;

    public  EmployeeDTO searchEmployeeByName(String nameValue) throws SQLException;

}
