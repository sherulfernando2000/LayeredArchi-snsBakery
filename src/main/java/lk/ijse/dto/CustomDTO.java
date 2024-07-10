package lk.ijse.dto;

import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data

public class CustomDTO {
//DailyRevenue
    private String date;

    private int count;

    public CustomDTO(String date, int count){
        this.date = date;
        this.count = count;
    }
}
