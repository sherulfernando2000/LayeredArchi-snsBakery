package lk.ijse.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class OrderProductDetailDTO {
    String orderId;
    String productId;

    double unitPrice;
     int qty;

     double total;


}
