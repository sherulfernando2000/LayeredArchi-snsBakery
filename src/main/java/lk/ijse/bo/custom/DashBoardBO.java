package lk.ijse.bo.custom;

import javafx.scene.chart.XYChart;
import lk.ijse.bo.SuperBO;
import lk.ijse.dto.CustomMostSellItemDTO;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.dto.EmployeeDTO;
import lk.ijse.dto.ProductDTO;

import java.sql.SQLException;
import java.util.List;

public interface DashBoardBO extends SuperBO {
    public List<CustomMostSellItemDTO> getMostSellItem() throws SQLException;

    public  double getDailyRevenue(String text) throws SQLException;

    public  int getProductSold(String desc) throws SQLException;

    public XYChart.Series getDateCount() throws SQLException;

    public  List<String> getProductDescription() throws SQLException;

    public List<CustomerDTO> getAllCustomer() throws SQLException,ClassNotFoundException;

    public  int getOderCount() throws SQLException, ClassNotFoundException;

    public  List<ProductDTO> getAllProduct() throws SQLException, ClassNotFoundException;

    public List<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException;

    public  double getDailyRevenue() throws SQLException;

    public  double getMonthlyRevenue() throws SQLException;


}
