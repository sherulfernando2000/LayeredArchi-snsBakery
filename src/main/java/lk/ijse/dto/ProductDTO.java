package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ProductDTO {
  private   String id;
  private String name;
  private String category;
  private int qty ;
  private double price ;

}
