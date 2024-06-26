package lk.ijse.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class CartTm {
    String code;
    String description;
    int qty;
    double unitPrice;
    double total;

}
