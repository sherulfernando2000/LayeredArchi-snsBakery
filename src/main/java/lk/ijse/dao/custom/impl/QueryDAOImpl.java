package lk.ijse.dao.custom.impl;

import lk.ijse.bo.custom.WeeklyReportBO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.QueryDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.entity.CustomEntity;
import lk.ijse.entity.CustomMostSellItem;
import lk.ijse.entity.CustomWeeklyRevenue;
import lk.ijse.view.DailyReportTm;
import lk.ijse.view.DailyWasteReportTm;
import lk.ijse.view.WeeklyReportTm;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {
    //DASHBOARD
    public List<CustomMostSellItem> getMostSellItem() throws SQLException {
        String sql = "SELECT p.pName AS MostSoldProductName, SUM(opd.qty) AS TotalQuantitySold\n" +
                "FROM orderProductDetail opd\n" +
                "JOIN product p ON opd.productId = p.productId\n" +
                "GROUP BY opd.productId, p.pName\n" +
                "ORDER BY SUM(opd.qty) DESC\n" +
                "LIMIT 6;\n" +
                "\n";;
       /* PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

*/
        ResultSet resultSet = SQLUtil.execute(sql);
        List<CustomMostSellItem> itemList = new ArrayList<>();

        while (resultSet.next()) {
            String name= resultSet.getString(1);
            int qty = Integer.parseInt(resultSet.getString(2));


            CustomMostSellItem mostSellItemTm = new CustomMostSellItem(name, qty);
            itemList.add(mostSellItemTm);
        }
        return itemList;

    }


    public  double getDailyRevenue(String text) throws SQLException {
        String sql = "SELECT date, SUM(totalAmount) AS DailyRevenue\n" +
                "FROM payment\n" +
                "WHERE date = ?\n" +
                "GROUP BY date;\n";
       /* PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);
        pstm.setObject(1,text);

        ResultSet resultSet = pstm.executeQuery();*/

        ResultSet resultSet = SQLUtil.execute(sql, text);

        double dailyRevenue=0;
        while (resultSet.next()) {
            dailyRevenue = Double.parseDouble(resultSet.getString(2));

        }
        return dailyRevenue;
    }


    public  int getProductSold(String desc) throws SQLException {
        String sql = "SELECT p.pName AS ProductName, SUM(opd.qty) AS QuantitySold\n" +
                "FROM orderProductDetail opd\n" +
                "JOIN product p ON opd.productId = p.productId\n" +
                "WHERE p.pName = ? \n" +
                "GROUP BY p.pName;\n";
        /*PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);
        pstm.setObject(1,desc);

        ResultSet resultSet = pstm.executeQuery();*/
        ResultSet resultSet = SQLUtil.execute(sql,desc);

        int qty =0;
        while (resultSet.next()) {
            qty = Integer.parseInt(resultSet.getString(2));

        }
        return qty;
    }

    public List<CustomEntity> getDateCount() throws SQLException {
        List<CustomEntity> dailyRevenueTmList = new ArrayList<>();
        PreparedStatement stm = null;

        ResultSet rst = null;
        try {
            rst = SQLUtil.execute("SELECT date, SUM(totalAmount) AS totalAmountSum\n" +
                    "FROM payment\n" +
                    "WHERE date >= CURDATE() - INTERVAL 6 DAY\n" +
                    "GROUP BY date\n" +
                    "ORDER BY date ASC;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while (rst.next()) {
            String date = rst.getString(1);
            int count = rst.getInt(2);

            CustomEntity dailyRevenueTm = new CustomEntity(date,count);
            dailyRevenueTmList.add(dailyRevenueTm);
        }
        return dailyRevenueTmList;

        /*while (true) {
            try {
                if (!rst.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }

            String date = null;
            try {
                date = rst.getString(1);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            int count = 0;
            try {
                count = rst.getInt(2);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DailyRevenueTm dailyRevenueTm = new DailyRevenueTm(date,count);
            dailyRevenueTmList.add(dailyRevenueTm);
        }
        return dailyRevenueTmList;*/
    }

    //DAILYREPORT
    public  List<DailyReportTm> getAll() throws SQLException {
        String sql = "SELECT\n" +
                "    o.date AS Date,\n" +
                "    p.pName AS ProductName,\n" +
                "    SUM(opd.qty) AS TotalProductsSold,\n" +
                "    SUM(opd.total) AS TotalIncome\n" +
                "FROM\n" +
                "    orders o\n" +
                "JOIN\n" +
                "    payment pm ON o.orderId = pm.orderId\n" +
                "JOIN\n" +
                "    orderproductdetail opd ON o.orderId = opd.orderId\n" +
                "JOIN\n" +
                "    product p ON opd.productId = p.productId\n" +
                "GROUP BY\n" +
                "    o.date,\n" +
                "    p.pName\n" +
                "ORDER BY\n" +
                "    o.date;\n";
        /*PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
*/

        ResultSet resultSet = SQLUtil.execute(sql);
        List<DailyReportTm> dailyRepoList = new ArrayList<>();

        while (resultSet.next()){
            Date day = Date.valueOf(resultSet.getString(1));
            String desc = resultSet.getString(2);
            int sold = Integer.parseInt(resultSet.getString(3));
            double total = Double.parseDouble(resultSet.getString(4));

            DailyReportTm dailyReportTm = new DailyReportTm(day,desc,sold, total);
            dailyRepoList.add(dailyReportTm);
        }
        return dailyRepoList;
    }

    public  double getDailyRevenue() throws SQLException {
        String sql = "SELECT\n" +
                "    DATE_FORMAT(date, '%Y-%m-%d') AS PaymentDate,\n" +
                "    SUM(totalAmount) AS DailyRevenue\n" +
                "FROM\n" +
                "    payment\n" +
                "WHERE\n" +
                "    date = CURDATE()\n" +
                "GROUP BY\n" +
                "    date;\n";
       /* PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
*/
        ResultSet resultSet = SQLUtil.execute(sql);

        double total =0;
        if (resultSet.next()){

            total = Double.parseDouble(resultSet.getString(2));

        }
        return total;
    }

    public  List<DailyWasteReportTm> getAllWaste() throws SQLException {
        String sql = "SELECT w.date, p.pName, w.wasteQty, w.disposeMethod\n" +
                "FROM wastemanage w\n" +
                "JOIN product p ON w.productId = p.productId\n" +
                "ORDER BY w.date ASC;\n";
        /*PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<DailyWasteReportTm> dailyRepoList = new ArrayList<>();*/
        ResultSet resultSet = SQLUtil.execute(sql);
        List<DailyWasteReportTm> dailyRepoList = new ArrayList<>();

        while (resultSet.next()){
            Date day = Date.valueOf(resultSet.getString(1));
            String desc = resultSet.getString(2);
            int qty = Integer.parseInt(resultSet.getString(3));


            DailyWasteReportTm dailyReportTm = new DailyWasteReportTm(day,desc,qty);
            dailyRepoList.add(dailyReportTm);
        }
        return dailyRepoList;
    }

    //Weekly report
    public List<WeeklyReportTm> getAllWeek() throws SQLException {
        String sql = "SELECT\n" +
                "    DATE_FORMAT(MIN(o.date), '%Y-%m-%d') AS WeekStartDate,\n" +
                "    DATE_FORMAT(MAX(o.date), '%Y-%m-%d') AS WeekEndDate,\n" +
                "    COUNT(*) AS WeeklyOrders,\n" +
                "    SUM(o.totalAmount) AS TotalAmount\n" +
                "FROM\n" +
                "    orders o\n" +
                "WHERE\n" +
                "    o.date BETWEEN (SELECT MIN(date) FROM orders) AND (SELECT MAX(date) FROM orders)\n" +
                "GROUP BY\n" +
                "    YEARWEEK(o.date, 1)\n" +
                "ORDER BY\n" +
                "    WeekStartDate;\n";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<WeeklyReportTm> weeklyRepoList = new ArrayList<>();

        while (resultSet.next()){
            Date weekStart = Date.valueOf(resultSet.getString(1));
            Date weekEnd = Date.valueOf(resultSet.getString(2));
            int orders = Integer.parseInt(resultSet.getString(3));
            double total = Double.parseDouble(resultSet.getString(4));

            WeeklyReportTm weeklyReportTm = new WeeklyReportTm(weekStart,weekEnd,orders, total);
            weeklyRepoList.add(weeklyReportTm);
        }
        return weeklyRepoList;
    }

    public  double getMonthlyRevenue() throws SQLException {
        String sql = "SELECT\n" +
                "    DATE_FORMAT(date, '%Y-%m') AS PaymentMonth,\n" +
                "    SUM(totalAmount) AS MonthlyRevenue\n" +
                "FROM\n" +
                "    payment\n" +
                "WHERE\n" +
                "    YEAR(date) = YEAR(CURDATE()) AND MONTH(date) = MONTH(CURDATE())\n" +
                "GROUP BY\n" +
                "    DATE_FORMAT(date, '%Y-%m')\n" +
                "ORDER BY\n" +
                "    PaymentMonth;\n";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();


        double total =0;
        if (resultSet.next()){

            total = Double.parseDouble(resultSet.getString(2));

        }
        return total;
    }

    public ResultSet weeklyRevenue() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT\n" +
                "    DATE_FORMAT(MIN(o.date), '%Y-%m-%d') AS WeekStartDate,\n" +
                "    DATE_FORMAT(MAX(o.date), '%Y-%m-%d') AS WeekEndDate,\n" +
                "    COUNT(*) AS WeeklyOrders,\n" +
                "    SUM(o.totalAmount) AS TotalAmount\n" +
                "FROM\n" +
                "    orders o\n" +
                "WHERE\n" +
                "    o.date BETWEEN (SELECT MIN(date) FROM orders) AND (SELECT MAX(date) FROM orders)\n" +
                "GROUP BY\n" +
                "    YEARWEEK(o.date, 1)\n" +
                "ORDER BY\n" +
                "    WeekStartDate;\n");

        return rst;
    }
}
