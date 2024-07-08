package lk.ijse.dao.custom;

import lk.ijse.dao.SuperDAO;
import lk.ijse.entity.CustomEntity;
import lk.ijse.entity.CustomMostSellItem;
import lk.ijse.view.DailyReportTm;
import lk.ijse.view.DailyWasteReportTm;
import lk.ijse.view.WeeklyReportTm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface QueryDAO extends SuperDAO {

    //DASHBOARD
    public List<CustomMostSellItem> getMostSellItem() throws SQLException;


    public double getDailyRevenue(String text) throws SQLException;


    public int getProductSold(String desc) throws SQLException;

    public List<CustomEntity> getDateCount() throws SQLException;

    //DAILYREPORT

    public  List<DailyReportTm> getAll() throws SQLException;

    public  double getDailyRevenue() throws SQLException;

    public  List<DailyWasteReportTm> getAllWaste() throws SQLException;

    public List<WeeklyReportTm> getAllWeek() throws SQLException;

    public  double getMonthlyRevenue() throws SQLException;

    public ResultSet weeklyRevenue() throws SQLException;


}
