package lk.ijse.bo.custom;

import javafx.scene.chart.XYChart;
import lk.ijse.bo.SuperBO;
import lk.ijse.view.WeeklyReportTm;

import java.sql.SQLException;
import java.util.List;

public interface WeeklyReportBO extends SuperBO {
    public List<WeeklyReportTm> getAllWeek() throws SQLException;

    public  double getMonthlyRevenue() throws SQLException;

    public XYChart.Series getlineChart1();
}
