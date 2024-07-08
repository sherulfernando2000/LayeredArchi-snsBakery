package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.ProductEmployeeBO;
import lk.ijse.dto.EmployeeDTO;
import lk.ijse.dto.ProductDTO;
import lk.ijse.dto.ProductEmployeeDTO;
import lk.ijse.entity.Employee;
import lk.ijse.entity.Product;
import lk.ijse.entity.ProductEmployee;
import lk.ijse.view.ProductEmployeeTm;
import lk.ijse.repository.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ProductEmployeeFormController {

    @FXML
    private JFXButton btnIngredient;

    @FXML
    private ComboBox<String> cmbEmployeeName;

    @FXML
    private ComboBox<String> cmbProductName;

    @FXML
    private TableColumn<?, ?> colAssignmentType;

    @FXML
    private TableColumn<?, ?> colEmployeeID;

    @FXML
    private TableColumn<?, ?> colEmployeeName;

    @FXML
    private TableColumn<?, ?> colProductId;

    @FXML
    private TableColumn<?, ?> colProductName;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TableView<ProductEmployeeTm> tblTask;


    @FXML
    private TextField txtAssignmentType;

    @FXML
    private TextField txtEmployeeId;

    @FXML
    private TextField txtProductId;

    ProductEmployeeBO productEmployeeBO = (ProductEmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PRODUCT_EMPLOYEE);

    public void initialize(){
        setCellValueFactory();
        loadAllProductEmployee();
        getEmployeeName();
        getProductName();
    }

    private void loadAllProductEmployee() {
        ObservableList<ProductEmployeeTm> obList = FXCollections.observableArrayList();

        try {
            List<ProductEmployeeDTO> orderList = productEmployeeBO.getAllProductEmployee();//ProductEmployeeRepo.getAll();
            for (ProductEmployeeDTO order : orderList) {

                String eName= EmployeeRepo.getName(order.getEmployeeId());
                String pName = ProductRepo.getName(order.getProductId());

                ProductEmployeeTm tm = new ProductEmployeeTm(
                        order.getEmployeeId(),
                        eName,
                        order.getProductId(),
                        pName,
                        order.getAssignmentType()

                );

                obList.add(tm);
            }

            tblTask.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colEmployeeID.setCellValueFactory(new PropertyValueFactory<>("eId"));
        colEmployeeName.setCellValueFactory(new PropertyValueFactory<>("eName"));
        colProductId.setCellValueFactory(new PropertyValueFactory<>("pId"));
        colProductName.setCellValueFactory(new PropertyValueFactory<>("pName"));
        colAssignmentType.setCellValueFactory(new PropertyValueFactory<>("assignmentType"));

    }

    private void getEmployeeName(){
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> nameList = productEmployeeBO.getEmployeeName();//EmployeeRepo.getName();
            for (String name: nameList) {
                obList.add(name);
            }
            cmbEmployeeName.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getProductName() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> descriptionList = productEmployeeBO.getProductName();//ProductRepo.getName();
            for (String description: descriptionList) {
                obList.add(description);
            }
            cmbProductName.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        ProductEmployeeTm productEmployeeTm = tblTask.getSelectionModel().getSelectedItem();
        try {
            //boolean isDeleted = ProductEmployeeRepo.detele(productEmployeeTm.getEId(), productEmployeeTm.getPId(),productEmployeeTm.getAssignmentType());
            boolean isDeleted = productEmployeeBO.deleteProductEmployee(productEmployeeTm.getEId(), productEmployeeTm.getPId(), productEmployeeTm.getAssignmentType());
            new Alert(Alert.AlertType.CONFIRMATION,"Supplier Order Deleted.").show();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnIngredientOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String employeeId = txtEmployeeId.getText();
        String productId = txtProductId.getText();
        String assignmentType = txtAssignmentType.getText();


        ProductEmployeeDTO productEmployee = new ProductEmployeeDTO(employeeId,productId,assignmentType );

        try {
            boolean isSaved = productEmployeeBO.saveProductEmployee(productEmployee);//ProductEmployeeRepo.save(productEmployee);
            if (isSaved ) {
                new Alert(Alert.AlertType.CONFIRMATION,"supplier order saved").show();
                loadAllProductEmployee();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void cmbEmployeeNameOnAction(ActionEvent event) {
        String nameValue = cmbEmployeeName.getValue();
        try {
            EmployeeDTO employee = productEmployeeBO.searchEmployeeByName(nameValue);//EmployeeRepo.searchByName(nameValue);
            if (employee != null) {
                txtEmployeeId.setText(employee.getEmployeeId());

            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cmbProductNameOnAction(ActionEvent event) {
        String nameValue = cmbProductName.getValue();
        try {
            ProductDTO product = productEmployeeBO.searchProductByName(nameValue);//ProductRepo.searchByName(nameValue);
            if (product != null) {
                txtProductId.setText(product.getId());

            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/employee_form.fxml"));
        this.rootNode.getChildren().removeAll();
        this.rootNode.getChildren().setAll(rootNode);
    }

}
