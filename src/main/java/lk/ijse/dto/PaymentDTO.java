package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentDTO {
    String paymentId;
    String paymentMethod ;
    String date ;
    double discountAmount;
    double totalAmount;
    String orderId;

    String discountType;
    double discountPrecentage;

}
