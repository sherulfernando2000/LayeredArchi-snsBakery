package lk.ijse.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import  javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.util.Duration;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.DashBoardBO;
import lk.ijse.dto.CustomMostSellItemDTO;
import lk.ijse.view.DailyRevenueTm;
import lk.ijse.view.MostSellItemTm;
import lk.ijse.repository.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javafx.scene.chart.PieChart;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

public class DashBoardFormController {
    public AnchorPane mainNode;
    @FXML
    private Button btnCustomer;

    @FXML
    private Button btnEmployee;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblUserName;


    @FXML
    private Text txtDailyRevenue;

    @FXML
    private Text txtMonthlyRevenue;

    @FXML
    private Text txtNoOfCustomers;

    @FXML
    private Text txtNoOfEmployee;

    @FXML
    private Text txtNoOfOrders;

    @FXML
    private Text txtNoOfProducts;

    @FXML
    private AreaChart<?, ?> barChart;

    @FXML
    private AreaChart<?, ?> barChart1;

    @FXML
    private PieChart pieChart;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField txtSearchByDate;

    @FXML
    private TextField txtSearchByProduct;

    @FXML
    private Text txtDailyRevenueSearch;

    @FXML
    private Text txtProductSoldSearch;

    private AutoCompletionBinding<String> autoCompletionBinding;
    private List<String> possibleSuggestion;

    private Set< String> possibleSugg;

    DashBoardBO dashBoardBO = (DashBoardBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.DASHBOARD);




    public void navigateTo(String url) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource(url));
        this.rootNode.getChildren().removeAll();
        this.rootNode.getChildren().setAll(rootNode);
    }

    @FXML
    void btnCustomerOnAction(ActionEvent event) throws IOException {
        navigateTo("/View/customer_from.fxml");



    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) throws IOException {
        navigateTo("/View/employee_form.fxml");

        /*AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/employee_form.fxml"));
        this.rootNode.getChildren().removeAll();
        this.rootNode.getChildren().setAll(rootNode);*/
    }

    public void initialize() throws SQLException {
        //int noOfCustomer = CustomerRepo.getAll().size();
        int noOfCustomer = 0;
        try {
            noOfCustomer = dashBoardBO.getAllCustomer().size();
            txtNoOfCustomers.setText(String.valueOf(noOfCustomer));
            //int noOfOrders = OrderRepo.getOderCount();
            int noOfOrders = dashBoardBO.getOderCount();
            txtNoOfOrders.setText(String.valueOf(noOfOrders));
            //int noOfProduct = ProductRepo.getAll().size();
            int noOfProduct = dashBoardBO.getAllProduct().size();
            txtNoOfProducts.setText(String.valueOf(noOfProduct));
            //int noOfEmployee = EmployeeRepo.getAll().size();
            int noOfEmployee = dashBoardBO.getAllEmployee().size();
            txtNoOfEmployee.setText(String.valueOf(noOfEmployee));
            //double dailyRevenue = DailyReportRepo.getDailyRevenue();
            double dailyRevenue = dashBoardBO.getDailyRevenue();
            txtDailyRevenue.setText(String.valueOf(dailyRevenue));
            //double monthlyRevenue = weeklyReportRepo.getMonthlyRevenue();
            double monthlyRevenue = dashBoardBO.getMonthlyRevenue();
            txtMonthlyRevenue.setText(String.valueOf(monthlyRevenue));

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        setDatetime();
        //lineChart();
        lineChart1();
        pieChartConnect();
        setTxtSearchByProduct();


    }

    public void setTxtSearchByProduct(){
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> descriptionList = dashBoardBO.getProductDescription();/*ProductRepo.getDescription();*/
            for (String description: descriptionList) {
                obList.add(description);
            }

            TextFields.bindAutoCompletion(txtSearchByProduct,obList);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }




    }


    private void setDatetime() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    LocalDateTime now = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd       HH:mm:ss");
                    String formattedDateTime = now.format(formatter);
                    lblDate.setText(formattedDateTime);
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    @FXML
    void btnDashboardOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashboard_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.mainNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Bakery Management System");
        /*Image icon = new Image(this.getClass().getResourceAsStream("/icon/sns-removebg-preview.png"));
        stage.getIcons().add(icon);*/

    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.mainNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Bakery Management System");
    }

    @FXML
    void btnOrderOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/order_form.fxml"));
        AnchorPane rootNode = loader.load();

        // Set the controller for the loaded FXML file
        OrderFormController controller = loader.getController();
        controller.initializeOrder(); // Call your custom initialization method

        this.rootNode.getChildren().removeAll();
        this.rootNode.getChildren().setAll(rootNode);

    }

    @FXML
    void btnPaymentOnAction(ActionEvent event) throws IOException {
        navigateTo("/View/payment_form.fxml");

    }

    @FXML
    void btnProductOnAction(ActionEvent event) throws IOException {
        navigateTo("/View/product_from.fxml");

    }

    @FXML
    void btnReportOnAction(ActionEvent event) throws IOException {
        navigateTo("/View/report.fxml");

    }

    @FXML
    void btnSuppliesOnAction(ActionEvent event) throws IOException {
        navigateTo("/View/Supplier_Form.fxml");

    }

    public void txtSet(){

    }




    public void lineChart1(){

        try {
            XYChart.Series series1 = dashBoardBO.getDateCount();
            barChart.getData().addAll(series1);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }





    public void setUsername(String username) {

        lblUserName.setText(username);
    }

    public void pieChartConnect() throws SQLException {
        //ObservableList<MostSellItemTm> obList = FXCollections.observableArrayList();

        List<CustomMostSellItemDTO> itemList = dashBoardBO.getMostSellItem(); /*DashboardRepo.getMostSellItem();*/

        for (CustomMostSellItemDTO sellItem : itemList) {

            ObservableList<PieChart.Data> pieChartData =
                    FXCollections.observableArrayList(
                            new PieChart.Data(sellItem.getName(), sellItem.getQty())
                    );  //PieChart has a static nested class Data

            pieChart.getData().addAll(pieChartData);
        }
    }

    @FXML
    void txtSearchByProductOnAction(ActionEvent event) {
        String desc = txtSearchByProduct.getText();

        if (!desc.isEmpty()) {
            try {
                int qty = dashBoardBO.getProductSold(desc);/*DashboardRepo.getProductSold(desc);*/
                txtProductSoldSearch.setText(String.valueOf(qty));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void txtSearchBydateOnAction(ActionEvent event) {
        if (!txtSearchByDate.getText().isEmpty()) {
            try {
                double dailyRevenue = dashBoardBO.getDailyRevenue(txtSearchByDate.getText());//DashboardRepo.getDailyRevenue(txtSearchByDate.getText());
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
