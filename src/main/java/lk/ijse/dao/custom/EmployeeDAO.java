package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface EmployeeDAO extends CrudDAO<Employee> {

    public  List<String> getName() throws SQLException;

    public  String getName(String employeeId) throws SQLException ;

    public  Employee searchByName(String nameValue) throws SQLException ;
}
