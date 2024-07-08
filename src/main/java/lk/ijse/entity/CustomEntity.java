package lk.ijse.entity;

import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data

public class CustomEntity {
//DailyRevenue
    private String date;

    private int count;

//mostsellitem

    private String name;

    private int qty ;

    public CustomEntity(String date, int count){
        this.date = date;
        this.count = count;
    }




}
