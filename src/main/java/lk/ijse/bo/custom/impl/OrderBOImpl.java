package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.OrderBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.*;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.*;
import lk.ijse.entity.*;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderBOImpl implements OrderBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Customer);
    ProductDAO productDAO = (ProductDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PRODUCT);

    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    OrderProductDetailsDAO orderProductDetailsDAO = (OrderProductDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER_PRODUCT_DETAILS);

    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);
    @Override
    public  String getCurrentId() throws SQLException, ClassNotFoundException {
        return orderDAO.getCurrentId();
    }

    @Override
    public  boolean saveOrder(OrderDTO order) throws SQLException, ClassNotFoundException {
       return orderDAO.save(new Order(order.getOrderId(), order.getStatus(),order.getDate(),order.getTotalAmount(),order.getCustomerId()));

    }


    @Override
    public  int getOderCount() throws SQLException, ClassNotFoundException {
        return orderDAO.getOderCount();

    }
    @Override
    public  boolean saveOrderProductDetails(List<OrderProductDetailDTO> odList) throws SQLException {
        for (OrderProductDetailDTO od : odList) {
            boolean isSaved = saveOrderProductDetails(od);
            if(!isSaved) {
                return false;
            }
        }
        return true;
    }
    @Override
    public   boolean saveOrderProductDetails(OrderProductDetailDTO od) throws SQLException {
        return orderProductDetailsDAO.save(new OrderProductDetail(od.getOrderId(),od.getProductId(),od.getUnitPrice(),od.getQty(),od.getTotal()));
    }
    @Override
    public  String getCurrentPaymentId() throws SQLException {
        return paymentDAO.getCurrentPaymentId();
    }

    @Override
    public  boolean savePayment(PaymentDTO payment) throws SQLException, ClassNotFoundException {
        return paymentDAO.save(new Payment(payment.getPaymentId(),payment.getPaymentMethod(),payment.getDate(),payment.getDiscountAmount(),payment.getTotalAmount(),payment.getOrderId(),payment.getDiscountType(), payment.getDiscountPrecentage()));

    }

    @Override
    public  List<String> getProductDescription() throws SQLException {
        return productDAO.getDescription();

    }
    @Override
    public ProductDTO searchProductByDescription(String description) throws SQLException {
        Product product = productDAO.searchByDescription(description);
        return new ProductDTO(product.getId(), product.getName(),product.getCategory(),product.getQty(),product.getPrice());
    }

    @Override
    public CustomerDTO searchCustomerTel(String tel) throws SQLException,ClassNotFoundException{
        Customer customer = customerDAO.searchTel(tel);
        return new CustomerDTO(customer.getId(),customer.getName(), customer.getTel(),customer.getAddress());
    }

    public  boolean placeOrder(OrderDTO orderDTO) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);


        try {
            OrderDTO orderDTO1 = orderDTO.getOrder();
            boolean isOrderSaved = orderDAO.save(new Order(orderDTO1.getOrderId(),orderDTO1.getStatus(),orderDTO1.getDate(),orderDTO1.getTotalAmount(),orderDTO1.getCustomerId()));/*OrderRepo.save(po.getOrder())*/;
            if (isOrderSaved) {
                List<OrderProductDetail> opdList = new ArrayList<>();
                for (OrderProductDetailDTO od:orderDTO.getOdList()) {
                    opdList.add(new OrderProductDetail(od.getOrderId(),od.getProductId(),od.getUnitPrice(),od.getQty(),od.getTotal()));
                }

                boolean isQtyUpdated = productDAO.update(opdList);
                if (isQtyUpdated) {
                    boolean isOrderDetailSaved = orderProductDetailsDAO.save(opdList);
                    if (isOrderDetailSaved) {
                        PaymentDTO payment = orderDTO.getPayment();
                        boolean isPaymentSaved = paymentDAO.save(new Payment(payment.getPaymentId(),payment.getPaymentMethod(),payment.getDate(),payment.getDiscountAmount(),payment.getTotalAmount(),payment.getOrderId(),payment.getDiscountType(), payment.getDiscountPrecentage()));
                        if (isPaymentSaved) {
                            connection.commit();
                            return true;
                        }
                    }
                }
            }
            connection.rollback();
            return false;
        } catch (Exception e) {
            connection.rollback();
            //new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            return false;

        } finally {
            connection.setAutoCommit(true);
        }
    }

}
