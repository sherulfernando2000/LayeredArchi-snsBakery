package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class OrderDTO {
   private String orderId;

   private String status;

   private Date date;
   private double totalAmount;

   private String customerId;

   private OrderDTO order;

   private List<OrderProductDetailDTO> odList;

   private PaymentDTO payment;

   public OrderDTO(OrderDTO order, List<OrderProductDetailDTO> odList, PaymentDTO payment){
      this.order = order;
      this.odList = odList;
      this.payment = payment;
   }

   public OrderDTO(String orderId, String status, Date date, double grossAmount, String customerId) {
      this.orderId = orderId;
      this.status = status;
      this.date = date;
      this.totalAmount = grossAmount;
      this.customerId = customerId;
   }
}
