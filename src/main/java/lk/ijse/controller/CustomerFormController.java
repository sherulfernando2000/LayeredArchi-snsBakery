package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Util.Regex;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.CustomerBO;
import lk.ijse.dao.custom.CustomerDAO;
import lk.ijse.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.entity.Customer;
import lk.ijse.model.EmailUtil;
import lk.ijse.view.CustomerTm;


import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public class CustomerFormController {
    @FXML
    private JFXButton btnClear;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TextField txtCustomerAddress;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtCustomerName;

    @FXML
    private TextField txtCustomerTel;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colCustomerName;

    @FXML
    private TableColumn<?, ?> colPhoneNo;


    @FXML
    private TableView<CustomerTm> tblCustomer;

    private String addressForEmail;

    private String nameForEmail;

    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Customer);


    public void initialize(){
        setCellValueFactory();
        loadAllCustomers();

    }


    private void setCellValueFactory() {
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPhoneNo.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
    }

    private void loadAllCustomers() {
        ObservableList<CustomerTm> obList = FXCollections.observableArrayList();
        try {
            List<CustomerDTO> customerList = customerBO.getAllCustomer();
            for (CustomerDTO customer : customerList) {
                CustomerTm tm = new CustomerTm(
                        customer.getId(),
                        customer.getName(),
                        customer.getTel(),
                        customer.getAddress()
                );

                obList.add(tm);
            }

            tblCustomer.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
            String id = tblCustomer.getSelectionModel().getSelectedItem().getId();
        Alert alert = new Alert(Alert.AlertType.INFORMATION,"Are you sure to delete?",ButtonType.OK,ButtonType.CANCEL);
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            try {
                boolean isDeleted = customerBO.deleteCustomer(id);
                new Alert(Alert.AlertType.CONFIRMATION,"Customer Deleted.",ButtonType.OK).show();
                clearFields();
                loadAllCustomers();
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }


    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtCustomerId.getText();
        String name = txtCustomerName.getText();
        String tel = txtCustomerTel.getText();
        String address = txtCustomerAddress.getText();
        addressForEmail = txtCustomerAddress.getText();
        nameForEmail = txtCustomerName.getText();

        CustomerDTO customerDTO = new CustomerDTO(id,name,tel,address);

        switch (isValied()){
            case 0:
                try {
                    boolean isSaved = customerBO.saveCustomer(customerDTO);
                    if (isSaved ) {
                        new Alert(Alert.AlertType.CONFIRMATION,"customer saved").show();
                        loadAllCustomers();
                        emailSent();
                        clearFields();
                    }
                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                } catch (MessagingException | ClassNotFoundException e) {
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }
                ;
                break;

            case 1:
                new Alert(Alert.AlertType.ERROR,"Invalid customer id format should be in C001 type").show();
                break;
             case 2:
                new Alert(Alert.AlertType.ERROR,"Invalid name format").show();
                break;
             case 3:
                new Alert(Alert.AlertType.ERROR,"Invalid phone no format").show();
                break;
             case 4:
                new Alert(Alert.AlertType.ERROR,"Invalid email format").show();
                break;

        }


    }

    private void clearFields() {
        txtCustomerId.setText("");
        txtCustomerName.setText("");
        txtCustomerTel.setText("");
        txtCustomerAddress.setText("");
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtCustomerId.getText();
        String name = txtCustomerName.getText();
        String tel = txtCustomerTel.getText();
        String address = txtCustomerAddress.getText();


        CustomerDTO customerDTO = new CustomerDTO(id,name,tel,address);

        try {
            boolean isUpdated = customerBO.updateCustomer(customerDTO);
            if (isUpdated ) {
                new Alert(Alert.AlertType.CONFIRMATION,"customer updated").show();
                clearFields();
                loadAllCustomers();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void txtCustomerIdOnAction(ActionEvent actionEvent) {
        String id = txtCustomerId.getText();

        try {
            CustomerDTO customer =  customerBO.searchCustomerId(id);
            if (customer != null) {
                txtCustomerId.setText(customer.getId());
                txtCustomerName.setText(customer.getName());
                txtCustomerAddress.setText(customer.getAddress());
                txtCustomerTel.setText(customer.getTel());
            } else {
                new Alert(Alert.AlertType.INFORMATION, "customer not found!").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    public void txtCustomerTelOnAction(ActionEvent actionEvent) {
        String tel = txtCustomerTel.getText();

        try {
            CustomerDTO customer =  customerBO.searchCustomerTel(tel);
            if (customer != null) {
                txtCustomerId.setText(customer.getId());
                txtCustomerName.setText(customer.getName());
                txtCustomerAddress.setText(customer.getAddress());
                txtCustomerTel.setText(customer.getTel());
            } else {
                new Alert(Alert.AlertType.INFORMATION, "customer not found!").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    public void txtCustomerIDOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.CID,txtCustomerId);
    }

    @FXML
    void txtCustomerPhonenoOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.Util.TextField.PHONENO,txtCustomerTel);
    }

    @FXML
    void txtCustomerEmailOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.Util.TextField.EMAIL,txtCustomerAddress);
    }

    @FXML
    void txtCustomerNameOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.Util.TextField.NAME,txtCustomerName);
    }

    public int isValied(){
        if (!Regex.setTextColor(lk.ijse.Util.TextField.CID,txtCustomerId)) return 1;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.NAME,txtCustomerName)) return 2;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.PHONENO,txtCustomerTel)) return 3;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.EMAIL,txtCustomerAddress)) return 4;
        return 0;
    }

    public void emailSent() throws MessagingException {
        String recipientEmail = addressForEmail; // recipient email
                String subject = "Welcome to S & S Bakery!";
                String body = "Dear "+nameForEmail+",\n"+"Welcome to S & S Bakery! Thank you for registering with us. Get ready for exclusive offers and seasonal discounts delivered straight to your inbox.\n" +
                        "\n" +
                        "We look forward to serving you delicious treats!";
                EmailUtil.sendEmail(recipientEmail, subject, body);

        // Show confirmation message to the user
        new Alert(Alert.AlertType.CONFIRMATION, "Customer saved &  Email sent.").show();
    }

    @FXML
    void btnEmailOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/View/offers_form.fxml"));
        this.rootNode.getChildren().removeAll();
        this.rootNode.getChildren().setAll(rootNode);
    }

}

