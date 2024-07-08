package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.EmployeeDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.entity.Employee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    public  boolean save(Employee employee) throws SQLException {

        String sql = "INSERT INTO employee VALUES (?,?,?,?,?,?)";
        return SQLUtil.execute(sql,employee.getEmployeeId(),employee.getEmployeeName(),employee.getEmployeeSalary(),employee.getEmployeePosition(),employee.getEmployeeAddress(),employee.getEmployeeTel());
    }

    public  boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM employee WHERE employeeId = ?";
        return SQLUtil.execute(sql,id);


    }

    public  boolean update(Employee employee) throws SQLException {
        String sql = "UPDATE employee SET eName = ?, salary = ?, postion = ?, address = ?, phoneNo = ? WHERE employeeId = ?";
        return SQLUtil.execute(sql,employee.getEmployeeName(),employee.getEmployeeSalary(),employee.getEmployeePosition(),employee.getEmployeeAddress(),employee.getEmployeeTel(),employee.getEmployeeId());

    }

    public  Employee searchId(String id) throws SQLException {
        String sql = "SELECT * FROM employee WHERE employeeId = ?";
        ResultSet resultSet = SQLUtil.execute(sql,id);


        while (resultSet.next()){
            String emp_id = resultSet.getString(1);
            String name = resultSet.getString(2);
            double salary = Double.parseDouble(resultSet.getString(3));
            String position = resultSet.getString(4);
            String address = resultSet.getString(5);
            String phoneNo = resultSet.getString(6);

            Employee employee = new Employee(emp_id,name,address,phoneNo,position,salary);
            return employee;
        }
        return null;
    }

    @Override
    public Employee searchTel(String tel) throws SQLException, ClassNotFoundException {
        return null;
    }

    public  List<Employee> getAll() throws SQLException {
        String sql = "SELECT * FROM employee";;

        ResultSet resultSet = SQLUtil.execute(sql);

        List<Employee> empList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            double salary = Double.parseDouble(resultSet.getString(3));
            String position = resultSet.getString(4);
            String address = resultSet.getString(5);
            String tel = resultSet.getString(6);

            Employee employee = new Employee(id, name, address, tel, position, salary);
            empList.add(employee);
        }
        return empList;

    }

    public  List<String> getName() throws SQLException {
        String sql = "SELECT eName FROM employee";
        ResultSet resultSet = SQLUtil.execute(sql);
        List<String> nameList = new ArrayList<>();
        while (resultSet.next()) {
            nameList.add(resultSet.getString(1));
        }
        return nameList;
    }

    public  String getName(String employeeId) throws SQLException {
        String sql = "SELECT eName FROM employee WHERE employeeId = ?";
        ResultSet resultSet = SQLUtil.execute(sql);

        if(resultSet.next()){
            String eName = resultSet.getString(1);

            return eName;
        }
        return null;


    }

    public  Employee searchByName(String nameValue) throws SQLException {
        String sql = "SELECT * FROM employee WHERE eName = ?";
        ResultSet resultSet = SQLUtil.execute(sql,nameValue);

        if (resultSet.next()) {
            return new Employee(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getDouble(6));
        }

        return null;
    }
}
