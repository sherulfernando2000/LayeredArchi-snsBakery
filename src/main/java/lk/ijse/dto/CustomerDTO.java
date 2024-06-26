package lk.ijse.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CustomerDTO {
   private String id;
   private String name;
   private String tel;
   private String address;

   public CustomerDTO(String id, String name, String tel, String address) {
      this.id = id;
      this.name = name;
      this.tel = tel;
      this.address = address;
   }
}
