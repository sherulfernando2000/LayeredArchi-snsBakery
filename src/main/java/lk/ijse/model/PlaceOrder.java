package lk.ijse.model;

import lk.ijse.entity.Order;
import lk.ijse.entity.OrderProductDetail;
import lk.ijse.entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlaceOrder {
    private Order order;
    private List<OrderProductDetail> odList;
    private Payment payment;

}
