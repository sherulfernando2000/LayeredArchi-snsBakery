package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dto.*;
import lk.ijse.entity.*;
import lk.ijse.model.PlaceOrder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface OrderBO extends SuperBO {
    public  String getCurrentId() throws SQLException, ClassNotFoundException;

    public  boolean saveOrder(OrderDTO order) throws SQLException, ClassNotFoundException;

    public  int getOderCount() throws SQLException, ClassNotFoundException;

    public  boolean saveOrderProductDetails(List<OrderProductDetailDTO> odList) throws SQLException;

    public   boolean saveOrderProductDetails(OrderProductDetailDTO od) throws SQLException;

    public  String getCurrentPaymentId() throws SQLException;

    public  boolean savePayment(PaymentDTO payment) throws SQLException, ClassNotFoundException;

    public  List<String> getProductDescription() throws SQLException;

    public ProductDTO searchProductByDescription(String description) throws SQLException;


    public CustomerDTO searchCustomerTel(String tel) throws SQLException,ClassNotFoundException;

    public  boolean placeOrder(OrderDTO orderDTO) throws SQLException;

}
