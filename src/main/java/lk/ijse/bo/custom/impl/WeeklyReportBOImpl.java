package lk.ijse.bo.custom.impl;

import javafx.scene.chart.XYChart;
import lk.ijse.bo.custom.WeeklyReportBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.QueryDAO;
import lk.ijse.view.WeeklyReportTm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class WeeklyReportBOImpl implements WeeklyReportBO {

    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY);
    @Override
    public List<WeeklyReportTm> getAllWeek() throws SQLException{
        return queryDAO.getAllWeek();
    }
    @Override
    public  double getMonthlyRevenue() throws SQLException{
        return queryDAO.getMonthlyRevenue();
    }
    @Override
    public XYChart.Series getlineChart1(){
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Bakery");

        ResultSet rst = null;
        try {
            rst = queryDAO.weeklyRevenue();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                if (!rst.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }

            String date = null;
            try {
                date = rst.getString(2);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            int count = 0;
            try {
                count = rst.getInt(4);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            series1.getData().add(new XYChart.Data<>(date, count));
        }
        return series1;
        //barChart1.getData().addAll(series1);
    }

}
