package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.EmployeeBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.EmployeeDAO;
import lk.ijse.dto.EmployeeDTO;
import lk.ijse.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    @Override
    public  boolean saveEmployee(EmployeeDTO employee) throws SQLException, ClassNotFoundException {
        return employeeDAO.save(new Employee(employee.getEmployeeId(),employee.getEmployeeName(),employee.getEmployeeAddress(),employee.getEmployeeTel(),employee.getEmployeePosition(),employee.getEmployeeSalary()));
    }
    @Override
    public  boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(id);
    }
    @Override
    public  boolean updateEmployee(EmployeeDTO employee) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(employee.getEmployeeId(),employee.getEmployeeName(),employee.getEmployeeAddress(),employee.getEmployeeTel(),employee.getEmployeePosition(),employee.getEmployeeSalary()));
    }
    @Override
    public  EmployeeDTO searchEmployeeId(String id) throws SQLException, ClassNotFoundException {
        Employee employee = employeeDAO.searchId(id);
        return new EmployeeDTO(employee.getEmployeeId(),employee.getEmployeeName(),employee.getEmployeeAddress(),employee.getEmployeeTel(),employee.getEmployeePosition(),employee.getEmployeeSalary());
    }

    @Override
    public EmployeeDTO searchEmployeeTel(String tel) throws SQLException, ClassNotFoundException {
        return null;
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
    public  List<String> getEmployeeName() throws SQLException {
        return employeeDAO.getName();
    }
    @Override
    public  String getEmployeeName(String employeeId) throws SQLException {
        return employeeDAO.getName(employeeId);
    }
    @Override
    public  EmployeeDTO searchEmployeeByName(String nameValue) throws SQLException{
        Employee employee = employeeDAO.searchByName(nameValue);
        return new EmployeeDTO(employee.getEmployeeId(),employee.getEmployeeName(),employee.getEmployeeAddress(),employee.getEmployeeTel(),employee.getEmployeePosition(),employee.getEmployeeSalary());
    }


}
