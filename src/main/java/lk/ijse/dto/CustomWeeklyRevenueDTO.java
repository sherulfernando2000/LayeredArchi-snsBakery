package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomWeeklyRevenueDTO {

    private Date weekStart;

    private Date weekEnd;

    private int orders;

    private double total;
}
