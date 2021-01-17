package ik.mint.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import ik.mint.pos.bo.BOFactry;
import ik.mint.pos.bo.custom.CustomerBO;
import ik.mint.pos.dto.AcccountDetailDTO;
import ik.mint.pos.dto.AccountDTO;
import ik.mint.pos.dto.CustomDTO;
import ik.mint.pos.dto.CustomerDTO;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddCustomerFormController implements Initializable {


    public JFXTextField txtDate;
    public JFXTextField txtFName;
    public JFXTextField txtCustomerId;
    public JFXComboBox cmbStatus;
    public JFXTextField txtLName;
    public JFXTextField TtxtCity;
    public JFXTextField txtAddress;
    public JFXTextField txtEmail;
    public JFXTextField txtPhoneNo;
    public JFXButton btnRegister;
    public JFXTextField txtAccountnumber;
    public JFXTextField txtStatus;
    public JFXComboBox cmbAccountType;
    public Pane AddCustomer;
    public JFXButton btnCreateAccount;

    static CustomerBO customerBO = (CustomerBO) BOFactry.getInstance().getBO(BOFactry.BOTypes.CUSTOMER);
    public JFXButton btnCashDeposit;
    public JFXButton btnCreate;
    public JFXButton btnCashWithdraw;
    public JFXButton btnCard;
    public JFXButton btnMoneyTransfer;

    public void generateDate() {
        txtDate.setText(LocalDate.now().toString());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        generateDate();
        getStatus();
        getAccountType();
        genarateAccountNumber();
    }

    public void getStatus() {
        cmbStatus.getItems().clear();
        cmbStatus.getItems().add("Mr");
        cmbStatus.getItems().add("Mrs");

    }
    public void genarateAccountNumber(){
        Random rand=new Random();
        int x=rand.nextInt(1000000000);
        txtAccountnumber.setText(String.valueOf(x));
    }

    public void getAccountType() {
        cmbAccountType.getItems().clear();
        cmbAccountType.getItems().add("Saving Account");
        cmbAccountType.getItems().add("Current Account");
        cmbAccountType.getItems().add("Recurring Deposit Account");
        cmbAccountType.getItems().add("Fixed Deposit Account");
        cmbAccountType.getItems().add("Demat Account");
        cmbAccountType.getItems().add("Loan Account");

    }
//    public void setCustomerId(){
//        try {
//          int Count = customerBO.getRegCount();
//            if (Count==0){
//                txtCustomerId.setText("C001");
//            }
//            if (Count>0 && Count<=8){
//                txtCustomerId.setText("C00"+(Count+1));
//            }
//            if (Count>=9 && Count<=98){
//                txtCustomerId.setText("C0"+(Count+1));
//            }
//            if (Count>=99){
//                txtCustomerId.setText("C"+(Count+1));
//            }
//        } catch (ClassNotFoundException ex) {
//            ex.printStackTrace();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }


    public void btnRegisterOnAction(ActionEvent actionEvent) {
        String CustomerId = txtCustomerId.getText();
        String FristName = txtFName.getText();
        String LastName = txtLName.getText();
        String City = TtxtCity.getText();
        String Address = txtAddress.getText();
        String EmailAddress = txtEmail.getText();
        String PhoneNo = txtPhoneNo.getText();
        try {
            if (CustomerId.length() > 0 && FristName.length() > 0 && LastName.length() > 0 && City.length() > 0 && Address.length() > 0 && EmailAddress.length() > 0 && PhoneNo.length() > 0) {
                String Status = cmbStatus.getSelectionModel().getSelectedItem().toString();
                CustomerDTO customerDTO = new CustomerDTO(CustomerId, Status, FristName, LastName, City, Address, EmailAddress, PhoneNo);

                boolean isAddedStudent = customerBO.addCustomer(customerDTO);
                if (isAddedStudent) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Customer Registerd Sussecfull", ButtonType.OK).show();
                    txtFName.setText(null);
                    txtLName.setText(null);
                    TtxtCity.setText(null);
                    txtAddress.setText(null);
                    txtEmail.setText(null);
                    txtPhoneNo.setText(null);
                } else {
                    new Alert(Alert.AlertType.WARNING, "Customer Registerd Fail", ButtonType.OK).show();
                }
            } else {
                new Alert(Alert.AlertType.WARNING, "Filed Can't Be Empty!",
                        ButtonType.OK).show();
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            new Alert(Alert.AlertType.WARNING, "This Customer Already Registerd", ButtonType.OK).show();
        } catch (NumberFormatException ex) {
            new Alert(Alert.AlertType.WARNING, "All Fields should be completed", ButtonType.OK).show();
        } catch (NullPointerException ex) {
            new Alert(Alert.AlertType.WARNING, "Customer Fields should be completed", ButtonType.OK).show();
        }
    }

    public void closeBtn(MouseEvent mouseEvent) {
        System.exit(0);
    }



    public void btnCreateAccountOnAction(ActionEvent actionEvent) {
        int x = 0;
        Integer AccountNumber = Integer.parseInt(txtAccountnumber.getText());
        String CustomerId = txtCustomerId.getText();
        String Status = txtStatus.getText();
        String Date = txtDate.getText();
        String AccountType = cmbAccountType.getSelectionModel().getSelectedItem().toString();

        try {
            ArrayList<CustomDTO> accountList = customerBO.getAllDetails(CustomerId);

            for (CustomDTO cDTO : accountList) {
                if (AccountType.equalsIgnoreCase(cDTO.getPassword())) {
                    x = 1;
                }
            }
                if(x==1){
                    new Alert(Alert.AlertType.WARNING, "This Customer Already Open This Account Type!",
                            ButtonType.OK).show();
                }


        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddCustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AddCustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AddCustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(x==0) {
    try {
        if (CustomerId.length() > 0 && Status.length() > 0 && Date.length() > 0) {

            ArrayList<AcccountDetailDTO> accountDetail=new ArrayList();

            int ref=0;
            String Detail="Create Account";
            Double Withdraw=0.00;
            Double Deposit=0.00;
            Double Balance=0.00;

            accountDetail.add(new AcccountDetailDTO(ref,Date,AccountNumber,Detail,Withdraw,Deposit,Balance));


            AccountDTO accountDTO = new AccountDTO(AccountNumber, CustomerId, AccountType, Date, Status,accountDetail);

            boolean isAddedStudent = customerBO.addAcccount(accountDTO);
            if (isAddedStudent) {
                new Alert(Alert.AlertType.CONFIRMATION, "Account Create Sussecfull", ButtonType.OK).show();

                txtCustomerId.setText(null);
                genarateAccountNumber();

            } else {
                new Alert(Alert.AlertType.WARNING, "Account Create Fail", ButtonType.OK).show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Filed Can't Be Empty!",
                    ButtonType.OK).show();
        }

    } catch (ClassNotFoundException ex) {
        ex.printStackTrace();
    } catch (NumberFormatException ex) {
        new Alert(Alert.AlertType.WARNING, "All Fields should be completed", ButtonType.OK).show();
    } catch (NullPointerException ex) {
        new Alert(Alert.AlertType.WARNING, "Account Fields should be completed", ButtonType.OK).show();
    } catch (SQLException ex) {
        new Alert(Alert.AlertType.WARNING, "This Customer Not Registerd", ButtonType.OK).show();
    }
}

        }

    public void btnCashDepositOnaction(ActionEvent actionEvent) throws IOException {
        Parent fxml= FXMLLoader.load(getClass().getResource("../views/CashDepositFrom.fxml"));
        AddCustomer.getChildren().removeAll();
        AddCustomer.getChildren().setAll(fxml);
    }

    public void btnCreateOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) this.AddCustomer.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../views/AddCustomerForm.fxml"))));
        window.show();
    }

    public void btnCashwithdrawOnaction(ActionEvent actionEvent) throws IOException {
        Parent fxml= FXMLLoader.load(getClass().getResource("../views/CashWithdrawFrom.fxml"));
        AddCustomer.getChildren().removeAll();
        AddCustomer.getChildren().setAll(fxml);
    }

    public void btnCardOnaction(ActionEvent actionEvent) throws IOException {
        Parent fxml= FXMLLoader.load(getClass().getResource("../views/CardForm.fxml"));
        AddCustomer.getChildren().removeAll();
        AddCustomer.getChildren().setAll(fxml);
    }

    public void btnMoneyTransferOnAction(ActionEvent actionEvent) throws IOException {
        Parent fxml= FXMLLoader.load(getClass().getResource("../views/MoneyTransferForm.fxml"));
        AddCustomer.getChildren().removeAll();
        AddCustomer.getChildren().setAll(fxml);
    }
}

