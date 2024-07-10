package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.ProductEmployeeDAO;
import lk.ijse.entity.ProductEmployee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductEmployeeDAOImpl implements ProductEmployeeDAO {
    @Override
    public  boolean save(ProductEmployee productEmployee) throws SQLException {
        String sql = "INSERT INTO productemployeedetail VALUES (?,?,?)";
        /*PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,productEmployee.getEmployeeId());
        pstm.setObject(2,productEmployee.getProductId());
        pstm.setObject(3,productEmployee.getAssignmentType());*/

        return SQLUtil.execute(sql,productEmployee.getEmployeeId(),productEmployee.getProductId(),productEmployee.getAssignmentType());



    }
    @Override
    public  List<ProductEmployee> getAll() throws SQLException {
        String sql = "SELECT * FROM productemployeedetail";

        /*PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
*/
        ResultSet resultSet = SQLUtil.execute(sql);
        List<ProductEmployee> orderList = new ArrayList<>();

        while (resultSet.next()) {
            String employeeID = resultSet.getString(1);
            String productId = resultSet.getString(2);
            String assignmentType = resultSet.getString(3);

            ProductEmployee productEmployee = new ProductEmployee(employeeID,productId,assignmentType);
            orderList.add(productEmployee);
        }
        return orderList;
    }
    @Override
    public  boolean delete(String eId, String pId, String assignmentType) throws SQLException {
        String sql = "DELETE FROM productemployeedetail WHERE employeeId = ? AND productId = ? AND  assignment_type = ? ;\n";
        /*PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,eId);
        pstm.setObject(2,pId);
        pstm.setObject(3,assignmentType);

        return pstm.executeUpdate()>0;*/
        return SQLUtil.execute(sql,eId,pId,assignmentType);
    }

    @Override
    public boolean update(ProductEmployee entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ProductEmployee searchId(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ProductEmployee searchTel(String tel) throws SQLException, ClassNotFoundException {
        return null;
    }


}
