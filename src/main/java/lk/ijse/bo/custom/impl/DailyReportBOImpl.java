package lk.ijse.bo.custom.impl;

import javafx.scene.chart.XYChart;
import lk.ijse.bo.custom.DailyReportBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.QueryDAO;
import lk.ijse.view.DailyReportTm;
import lk.ijse.view.DailyWasteReportTm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DailyReportBOImpl implements DailyReportBO {
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY);
    @Override
    public  List<DailyReportTm> getAll() throws SQLException {
        return queryDAO.getAll();
    }
    @Override
    public  double getDailyRevenue() throws SQLException{
        return queryDAO.getDailyRevenue();
    }
    @Override
    public  double getDailyRevenue(String text) throws SQLException {
        return queryDAO.getDailyRevenue(text);
    }
    @Override
    public  List<DailyWasteReportTm> getAllWaste() throws SQLException{
        return queryDAO.getAllWaste();
    }
    @Override
    public <T> XYChart.Series getlineChart() throws SQLException {
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Bakery");



        ResultSet rst = SQLUtil.execute("SELECT date, SUM(totalAmount) AS totalAmountSum\n" +
                "FROM payment\n" +
                "WHERE date >= CURDATE() - INTERVAL 6 DAY\n" +
                "GROUP BY date\n" +
                "ORDER BY date ASC;");
        while (rst.next()){
            String date = rst.getString(1);
            int count = rst.getInt(2);
            series1.getData().add(new XYChart.Data<>(date, count));
        }


        return series1;
    }
}
