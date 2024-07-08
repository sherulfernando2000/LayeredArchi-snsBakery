package lk.ijse.bo.custom;

import javafx.scene.chart.XYChart;
import lk.ijse.bo.SuperBO;
import lk.ijse.view.DailyReportTm;
import lk.ijse.view.DailyWasteReportTm;

import java.sql.SQLException;
import java.util.List;

public interface DailyReportBO extends SuperBO {
    public List<DailyReportTm> getAll() throws SQLException;

    public  double getDailyRevenue() throws SQLException;

    public  List<DailyWasteReportTm> getAllWaste() throws SQLException;
    public <T> XYChart.Series getlineChart() throws SQLException;
    public  double getDailyRevenue(String text) throws SQLException;
}
