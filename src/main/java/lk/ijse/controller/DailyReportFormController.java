package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.DailyReportBO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.db.DbConnection;
import lk.ijse.view.DailyReportTm;
import lk.ijse.view.DailyWasteReportTm;
import lk.ijse.repository.DailyReportRepo;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javafx.scene.chart.AreaChart;
import lk.ijse.repository.DashboardRepo;

public class DailyReportFormController {

    @FXML
    private JFXButton btnSupplier;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TableColumn<?, ?> colDay;

    @FXML
    private TableColumn<?, ?> colDesc;

    @FXML
    private TableColumn<?, ?> colSold;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableView<DailyReportTm> tableDailyReport;

    @FXML
    private AreaChart<?, ?> barChart;

    @FXML
    private TextField txtSearchByDate;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Text txtDailyRevenueSearch;

    @FXML
    private TableColumn<?, ?> colDayW;

    @FXML
    private TableColumn<?, ?> colDescriptionW;

    @FXML
    private TableColumn<?, ?> colQtyW;

    @FXML
    private TableView<DailyWasteReportTm> tblWaste;


DailyReportBO dailyReportBO = (DailyReportBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.DAILYREPORT);

    public void initialize(){
        setCellValueFactory();
        setCellValueFactory1();
        loadAllDailyReport();
        lineChart();
        loadAllDailyWaste();
    }

    private void setCellValueFactory() {
        colDay.setCellValueFactory(new PropertyValueFactory<>("day"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("desc"));
        colSold.setCellValueFactory(new PropertyValueFactory<>("sold"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
    }

    private void loadAllDailyReport(){
        ObservableList<DailyReportTm> obList = FXCollections.observableArrayList();
        try {
            List<DailyReportTm> repoList = dailyReportBO.getAll();/*DailyReportRepo.getAll();*/
            for (DailyReportTm dailyReportTm : repoList) {
                DailyReportTm tm = new DailyReportTm(
                        dailyReportTm.getDay(),
                        dailyReportTm.getDesc(),
                        dailyReportTm.getSold(),
                        dailyReportTm.getTotal()
                );

                obList.add(tm);
            }

            tableDailyReport.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnMonthlyIncomeOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/View/weekly_report_form.fxml"));
        this.rootNode.getChildren().removeAll();
        this.rootNode.getChildren().setAll(rootNode);
    }

    public void lineChart(){
        /*XYChart.Series series1 = new XYChart.Series();
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
        }*/

        try {
            XYChart.Series series1 = dailyReportBO.getlineChart();
            barChart.getData().addAll(series1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void setCellValueFactory1() {
        colDayW.setCellValueFactory(new PropertyValueFactory<>("day"));
        colDescriptionW.setCellValueFactory(new PropertyValueFactory<>("desc"));
        colQtyW.setCellValueFactory(new PropertyValueFactory<>("qty"));

    }


    private void loadAllDailyWaste(){
        ObservableList<DailyWasteReportTm> obList = FXCollections.observableArrayList();
        try {
            List<DailyWasteReportTm> repoList = DailyReportRepo.getAllWaste();
            for (DailyWasteReportTm dailyWasteReportTm : repoList) {
                DailyWasteReportTm tm = new DailyWasteReportTm(
                        dailyWasteReportTm.getDay(),
                        dailyWasteReportTm.getDesc(),
                        dailyWasteReportTm.getQty()
                );

                obList.add(tm);
            }

            tblWaste.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void txtSearchBydateOnAction(ActionEvent event) {
        if (!txtSearchByDate.getText().isEmpty()) {
            try {
                double dailyRevenue = dailyReportBO.getDailyRevenue(txtSearchByDate.getText());/*DashboardRepo.getDailyRevenue(txtSearchByDate.getText());*/
                txtDailyRevenueSearch.setText(String.valueOf(dailyRevenue));
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        }else{
            new Alert(Alert.AlertType.ERROR,"Input a date").show();
        }
    }

    @FXML
    void getDate(ActionEvent event) {
        LocalDate myDate = datePicker.getValue();
        String myFormattedDate = myDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        txtSearchByDate.setText(myFormattedDate);
        txtSearchByDate.requestFocus();
    }



}
