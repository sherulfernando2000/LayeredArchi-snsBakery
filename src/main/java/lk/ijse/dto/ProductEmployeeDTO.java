package lk.ijse.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductEmployeeDTO {
   private String employeeId ;
   private String productId ;
   private String assignmentType;
}
