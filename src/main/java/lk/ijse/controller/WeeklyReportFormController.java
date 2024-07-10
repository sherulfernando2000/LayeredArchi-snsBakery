package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.WeeklyReportBO;
import lk.ijse.view.WeeklyReportTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class WeeklyReportFormController {

    @FXML
    private AreaChart<?, ?> barChart1;

    @FXML
    private JFXButton btnDailyIncome;

    @FXML
    private TableColumn<?, ?> colOrders;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colWeekEnd;

    @FXML
    private TableColumn<?, ?> colWeekStart;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TableView<WeeklyReportTm> tableWeeklyReport;

    WeeklyReportBO weeklyReportBO = (WeeklyReportBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.WEEKLYREPORT);

    public void initialize(){
        setCellValueFactory();
        loadAllWeeklyReport();
        lineChart1();
    }

    private void setCellValueFactory() {
        colWeekStart.setCellValueFactory(new PropertyValueFactory<>("weekStart"));
        colWeekEnd.setCellValueFactory(new PropertyValueFactory<>("weekEnd"));
        colOrders.setCellValueFactory(new PropertyValueFactory<>("orders"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
    }

    private void loadAllWeeklyReport(){
        ObservableList<WeeklyReportTm> obList = FXCollections.observableArrayList();
        try {
            List<WeeklyReportTm> repoList = weeklyReportBO.getAllWeek();
            for (WeeklyReportTm weeklyReportTm: repoList) {
                WeeklyReportTm tm = new WeeklyReportTm(
                        weeklyReportTm.getWeekStart(),
                        weeklyReportTm.getWeekEnd(),
                        weeklyReportTm.getOrders(),
                        weeklyReportTm.getTotal()
                );

                obList.add(tm);
            }

            tableWeeklyReport.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void lineChart1(){
       try {
           XYChart.Series series1 = weeklyReportBO.getlineChart1();
           barChart1.getData().addAll(series1);
       } catch (Exception e) {
           throw new RuntimeException(e);
       }

    }


    @FXML
    void btnDailyIncomeOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/View/report.fxml"));
        this.rootNode.getChildren().removeAll();
        this.rootNode.getChildren().setAll(rootNode);
    }






}
